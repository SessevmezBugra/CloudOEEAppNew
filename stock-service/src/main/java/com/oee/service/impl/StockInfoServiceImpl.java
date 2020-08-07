package com.oee.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.dto.PlantDto;
import com.oee.dto.StockDto;
import com.oee.dto.WarehouseDto;
import com.oee.entity.StockMovement;
import com.oee.error.EntityNotFoundException;
import com.oee.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.StockInfo;
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
    public StockInfo create(StockInfo stockInfo) {
        return stockRepository.save(stockInfo);
    }

    @Override
    public StockInfo update(StockInfo stockInfo) {
        return stockRepository.save(stockInfo);
    }

    @Override
    public Boolean delete(Long id) {
        stockRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public StockInfo getById(Long id) {
        return stockRepository.findById(id).get();
    }

    @Override
    public List<StockInfo> getByIds(List<Long> ids) {
        return stockRepository.findAllById(ids);
    }

    @Override
    public List<StockInfo> getByWarehouseId(Long warehouseId) {
        return stockRepository.findByWarehouseId(warehouseId);
    }

    @Override
    public Boolean deleteByWarehouseIds(List<Long> warehouseIds) {
        stockRepository.deleteByWarehouseIdIn(warehouseIds);
        return Boolean.TRUE;
    }

    @Override
    public StockInfo addStock(StockInfo stockInfo) {
        if (stockInfo.getMaterialId() == null || stockInfo.getWarehouseId() == null) {
            throw new RuntimeException("MaterialId veya WarehouseId bos olamaz!");
        }
        System.err.println(stockInfo.getMaterialId() + " " + stockInfo.getWarehouseId());
        //O malzeme o depoda var mi yok mu kontrolu yapilir. Varsa uzerine belirtilen miktar eklenir. Yoksa stok olusturulur.
        StockInfo foundStockInfo = stockRepository.findByMaterialIdAndWarehouseId(stockInfo.getMaterialId(), stockInfo.getWarehouseId());
        if (foundStockInfo != null) {
            System.err.println("test1");
            foundStockInfo.setQuantity(foundStockInfo.getQuantity() + stockInfo.getQuantity());
            System.err.println(foundStockInfo.getQuantity());
            stockRepository.save(foundStockInfo);
            StockMovement stockMovement = new StockMovement();
            stockMovement.setPositive(true);
            stockMovement.setQuantity(stockInfo.getQuantity());
            stockMovement.setStock(foundStockInfo);
            stockMovementService.create(stockMovement);
            return foundStockInfo;
        } else {
            System.err.println("test32");
            stockRepository.save(stockInfo);
            StockMovement stockMovement = new StockMovement();
            stockMovement.setPositive(true);
            stockMovement.setQuantity(stockInfo.getQuantity());
            stockMovement.setStock(stockInfo);
            stockMovementService.create(stockMovement);
            return stockInfo;
        }
    }

    @Override
    public StockInfo extractStock(StockInfo stockInfo) {
        // TODO Auto-generated method stub
        if (stockInfo.getMaterialId() == null || stockInfo.getWarehouseId() == null) {
            throw new RuntimeException("MaterialId veya WarehouseId bos olamaz!");
        }
        //O malzeme o depoda var mi yok mu kontrolu yapilir. Varsa uzerine belirtilen miktar eklenir. Yoksa stok olusturulur.
        StockInfo foundStockInfo = stockRepository.findByMaterialIdAndWarehouseId(stockInfo.getMaterialId(), stockInfo.getWarehouseId());
        if (foundStockInfo == null) {
            throw new RuntimeException("Depoda bu malzeme bulunmamaktadir.");
        }
        if (foundStockInfo.getQuantity() < stockInfo.getQuantity()) {
            throw new RuntimeException("Depoda bu miktarda malzeme bulunmamaktadir.");
        }

        foundStockInfo.setQuantity(foundStockInfo.getQuantity() - stockInfo.getQuantity());
        stockRepository.save(foundStockInfo);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setPositive(false);
        stockMovement.setQuantity(stockInfo.getQuantity());
        stockMovement.setStock(foundStockInfo);
        stockMovementService.create(stockMovement);
        return foundStockInfo;

    }

    @Override
    public StockInfo getByWarehouseIdAndMaterialId(Long warehouseId, Long materialId) {
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
                }
            }
            for (int l = 0; l < plantDto.getWarehouses().size(); l++) {
                if(stockDtos.get(i).getWarehouseId() == plantDto.getWarehouses().get(l).getWarehouseId()) {
                    stockDtos.get(i).setWarehouseName(plantDto.getWarehouses().get(l).getWarehouseName());
                }
            }
        }
        return stockDtos;
    }

    @Override
    public List<StockInfo> extractAllStock(List<StockInfo> stockInfos) {
        List<Long> ids = stockInfos.stream().map(StockInfo::getStockId).collect(Collectors.toList());
        List<StockInfo> foundStocks = stockRepository.findAllById(ids);
        List<StockMovement> stockMovements = new ArrayList<>();
        for (StockInfo foundStock : foundStocks) {
            StockInfo stockMov = stockInfos.stream().filter(stockInfo -> stockInfo.getStockId() == foundStock.getStockId()).findFirst().orElseThrow(() -> new EntityNotFoundException("Boyle bir stok bulunmamaktadir!"));
            if (foundStock.getQuantity() < stockMov.getQuantity()) {
                throw new RuntimeException("Depoda bu teyit icin yeterli stok bulunmamaktadir!");
            }
            foundStock.setQuantity(foundStock.getQuantity() - stockMov.getQuantity());
            StockMovement stockMovement = new StockMovement();
            stockMovement.setPositive(false);
            stockMovement.setQuantity(stockMov.getQuantity());
            stockMovement.setStock(foundStock);
            stockMovements.add(stockMovement);
        }
        stockRepository.saveAll(foundStocks);
        stockMovementService.createAll(stockMovements);
        return stockInfos;
    }

}
