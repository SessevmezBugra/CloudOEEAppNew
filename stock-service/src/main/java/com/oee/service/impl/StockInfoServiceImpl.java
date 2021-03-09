package com.oee.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.dto.MaterialDto;
import com.oee.dto.PlantDto;
import com.oee.dto.StockDto;
import com.oee.dto.WarehouseDto;
import com.oee.entity.Stock;
import com.oee.entity.StockMovement;
import com.oee.error.EntityNotFoundException;
import com.oee.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.repository.StockRepository;
import com.oee.service.StockInfoService;

@Service
@RequiredArgsConstructor
public class StockInfoServiceImpl implements StockInfoService {

    private final StockRepository stockRepository;
    private final StockMovementService stockMovementService;
    private final MainDataServiceClient mainDataServiceClient;
    private final ModelMapper modelMapper;

    @Override
    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock update(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Boolean delete(Long id) {
        stockRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Stock getById(Long id) {
        return stockRepository.findById(id).get();
    }

    @Override
    public List<Stock> getByIds(List<Long> ids) {
        return stockRepository.findAllById(ids);
    }

    @Override
    public List<StockDto> getByWarehouseId(Long warehouseId) {
        PlantDto plantDto = mainDataServiceClient.getPlantByWarehouseId(warehouseId).getBody();
        List<MaterialDto> materialDtos = plantDto.getMaterials();
        List<Stock> stocks = stockRepository.findByWarehouseId(warehouseId);
        List<StockDto> stockDtos = Arrays.asList(modelMapper.map(stocks, StockDto[].class));
        for (StockDto stockDto : stockDtos) {
            for (MaterialDto materialDto : materialDtos) {
                if(stockDto.getMaterialId() == materialDto.getMaterialId()) {
                    stockDto.setMaterialDesc(materialDto.getMaterialDesc());
                    stockDto.setMaterialNumber(materialDto.getMaterialNumber());
                    break;
                }
            }
        }
        return stockDtos;
    }

    @Override
    public Boolean deleteByWarehouseIds(List<Long> warehouseIds) {
        stockRepository.deleteByWarehouseIdIn(warehouseIds);
        return Boolean.TRUE;
    }

    @Override
    public Stock addStock(Stock stock) {
        if (stock.getMaterialId() == null || stock.getWarehouseId() == null) {
            throw new RuntimeException("MaterialId veya WarehouseId bos olamaz!");
        }
        System.err.println(stock.getMaterialId() + " " + stock.getWarehouseId());
        //O malzeme o depoda var mi yok mu kontrolu yapilir. Varsa uzerine belirtilen miktar eklenir. Yoksa stok olusturulur.
        Stock foundStock = stockRepository.findByMaterialIdAndWarehouseId(stock.getMaterialId(), stock.getWarehouseId());
        if (foundStock != null) {
            System.err.println("test1");
            foundStock.setQuantity(foundStock.getQuantity() + stock.getQuantity());
            System.err.println(foundStock.getQuantity());
            stockRepository.save(foundStock);
            StockMovement stockMovement = new StockMovement();
            stockMovement.setIsPositive(true);
            stockMovement.setQuantity(stock.getQuantity());
            stockMovement.setStock(foundStock);
            stockMovementService.create(stockMovement);
            return foundStock;
        } else {
            System.err.println("test32");
            stockRepository.save(stock);
            StockMovement stockMovement = new StockMovement();
            stockMovement.setIsPositive(true);
            stockMovement.setQuantity(stock.getQuantity());
            stockMovement.setStock(stock);
            stockMovementService.create(stockMovement);
            return stock;
        }
    }

    @Override
    public Stock extractStock(Stock stock) {
        // TODO Auto-generated method stub
        if (stock.getMaterialId() == null || stock.getWarehouseId() == null) {
            throw new RuntimeException("MaterialId veya WarehouseId bos olamaz!");
        }
        //O malzeme o depoda var mi yok mu kontrolu yapilir. Varsa uzerine belirtilen miktar eklenir. Yoksa stok olusturulur.
        Stock foundStock = stockRepository.findByMaterialIdAndWarehouseId(stock.getMaterialId(), stock.getWarehouseId());
        if (foundStock == null) {
            throw new RuntimeException("Depoda bu malzeme bulunmamaktadir.");
        }
        if (foundStock.getQuantity() < stock.getQuantity()) {
            throw new RuntimeException("Depoda bu miktarda malzeme bulunmamaktadir.");
        }

        foundStock.setQuantity(foundStock.getQuantity() - stock.getQuantity());
        stockRepository.save(foundStock);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setIsPositive(false);
        stockMovement.setQuantity(stock.getQuantity());
        stockMovement.setStock(foundStock);
        stockMovementService.create(stockMovement);
        return foundStock;

    }

    @Override
    public Stock getByWarehouseIdAndMaterialId(Long warehouseId, Long materialId) {
        return stockRepository.findByMaterialIdAndWarehouseId(materialId, warehouseId);
    }

    @Override
    public List<StockDto> getByPlantId(Long plantId) {
        PlantDto plantDto = mainDataServiceClient.getPlantById(plantId).getBody();
        List<Long> warehouseIds = plantDto.getWarehouses().stream().map(WarehouseDto::getWarehouseId).collect(Collectors.toList());
        List<StockDto> stockDtos = Arrays.asList(modelMapper.map(stockRepository.findByWarehouseIdIn(warehouseIds), StockDto[].class));
        for (int i = 0; i < stockDtos.size(); i++) {
            for (int k = 0; k < plantDto.getMaterials().size(); k++) {
                if(stockDtos.get(i).getMaterialId() == plantDto.getMaterials().get(k).getMaterialId()) {
                    stockDtos.get(i).setMaterialDesc(plantDto.getMaterials().get(k).getMaterialDesc());
                    stockDtos.get(i).setMaterialNumber(plantDto.getMaterials().get(k).getMaterialNumber());
                    break;
                }
            }
            for (int l = 0; l < plantDto.getWarehouses().size(); l++) {
                if(stockDtos.get(i).getWarehouseId() == plantDto.getWarehouses().get(l).getWarehouseId()) {
                    stockDtos.get(i).setWarehouseName(plantDto.getWarehouses().get(l).getWarehouseName());
                    break;
                }
            }
        }
        return stockDtos;
    }

    @Override
    public List<Stock> extractAllStock(List<Stock> stocks) {
        List<Long> ids = stocks.stream().map(Stock::getStockId).collect(Collectors.toList());
        List<Stock> foundStocks = stockRepository.findAllById(ids);
        List<StockMovement> stockMovements = new ArrayList<>();
        for (Stock foundStock : foundStocks) {
            Stock stockMov = stocks.stream().filter(stockInfo -> stockInfo.getStockId() == foundStock.getStockId()).findFirst().orElseThrow(() -> new EntityNotFoundException("Boyle bir stok bulunmamaktadir!"));
            if (foundStock.getQuantity() < stockMov.getQuantity()) {
                throw new RuntimeException("Depoda bu teyit icin yeterli stok bulunmamaktadir!");
            }
            foundStock.setQuantity(foundStock.getQuantity() - stockMov.getQuantity());
            StockMovement stockMovement = new StockMovement();
            stockMovement.setIsPositive(false);
            stockMovement.setQuantity(stockMov.getQuantity());
            stockMovement.setStock(foundStock);
            stockMovements.add(stockMovement);
        }
        stockRepository.saveAll(foundStocks);
        stockMovementService.createAll(stockMovements);
        return stocks;
    }

}
