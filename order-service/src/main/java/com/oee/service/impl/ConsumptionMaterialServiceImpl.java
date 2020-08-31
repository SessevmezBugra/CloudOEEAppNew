package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.client.StockServiceClient;
import com.oee.dto.ConsumptionStockDto;
import com.oee.dto.MaterialDto;
import com.oee.dto.StockDto;
import com.oee.dto.WarehouseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.ConsumptionStock;
import com.oee.repository.ConsumptionMaterialRepository;
import com.oee.service.ConsumptionMaterialService;

@Service
public class ConsumptionMaterialServiceImpl implements ConsumptionMaterialService{

	private final ConsumptionMaterialRepository consumptionMaterialRepository;
	private final StockServiceClient stockServiceClient;
	private final MainDataServiceClient mainDataServiceClient;
	private final ModelMapper modelMapper;
	
	public ConsumptionMaterialServiceImpl(ConsumptionMaterialRepository consumptionMaterialRepository, StockServiceClient stockServiceClient, MainDataServiceClient mainDataServiceClient, ModelMapper modelMapper) {
		this.consumptionMaterialRepository = consumptionMaterialRepository;
		this.stockServiceClient = stockServiceClient;
		this.mainDataServiceClient = mainDataServiceClient;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public ConsumptionStock create(ConsumptionStock consumptionStock) {
		return consumptionMaterialRepository.save(consumptionStock);
	}

	@Override
	public ConsumptionStock update(ConsumptionStock consumptionStock) {
		return consumptionMaterialRepository.save(consumptionStock);
	}

	@Override
	public Boolean delete(Long id) {
		consumptionMaterialRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ConsumptionStock getById(Long id) {
		return consumptionMaterialRepository.findById(id).get();
	}

	@Override
	public List<ConsumptionStockDto> getByOrderId(Long id) {
		List<ConsumptionStock> consumptionStocks = consumptionMaterialRepository.findByOrderOrderId(id);
		List<ConsumptionStockDto> consumptionStockDtos = Arrays.asList(modelMapper.map(consumptionStocks, ConsumptionStockDto[].class));
		List<Long> stockIds = consumptionStocks.stream().map(ConsumptionStock::getStockId).collect(Collectors.toList());
		List<StockDto> stockDtos = stockServiceClient.getStockByIds(stockIds).getBody();
		List<Long> materialIds = stockDtos.stream().map(StockDto::getMaterialId).collect(Collectors.toList());
		List<Long> warehouseIds = stockDtos.stream().map(StockDto::getWarehouseId).collect(Collectors.toList());
		List<MaterialDto> materialDtos = mainDataServiceClient.getMaterialsByIds(materialIds).getBody();
		List<WarehouseDto> warehouseDtos = mainDataServiceClient.getWarehousesByIds(warehouseIds).getBody();

		for (ConsumptionStockDto consumptionStockDto : consumptionStockDtos) {
			for (StockDto stockDto : stockDtos) {
				if (consumptionStockDto.getStockId() == stockDto.getStockId()) {
					consumptionStockDto.setQuantity(stockDto.getQuantity());
					consumptionStockDto.setMaterialId(stockDto.getMaterialId());
					consumptionStockDto.setWarehouseId(stockDto.getWarehouseId());
					break;
				}
			}
			for (MaterialDto materialDto : materialDtos) {
				if (materialDto.getMaterialId() == consumptionStockDto.getMaterialId()){
					consumptionStockDto.setMaterialDesc(materialDto.getMaterialDesc());
					consumptionStockDto.setMaterialNumber(materialDto.getMaterialNumber());
					break;
				}
			}
			for (WarehouseDto warehouseDto : warehouseDtos) {
				if(consumptionStockDto.getWarehouseId() == warehouseDto.getWarehouseId()) {
					consumptionStockDto.setWarehouseName(warehouseDto.getWarehouseName());
					break;
				}
			}
		}

		return consumptionStockDtos;
	}

	@Override
	public List<ConsumptionStockDto> getByOrderIdWithoutWarehouseInfo(Long id) {
		List<ConsumptionStock> consumptionStocks = consumptionMaterialRepository.findByOrderOrderId(id);
		List<ConsumptionStockDto> consumptionStockDtos = Arrays.asList(modelMapper.map(consumptionStocks, ConsumptionStockDto[].class));
		List<Long> stockIds = consumptionStocks.stream().map(ConsumptionStock::getStockId).collect(Collectors.toList());
		List<StockDto> stockDtos = stockServiceClient.getStockByIds(stockIds).getBody();
		List<Long> materialIds = stockDtos.stream().map(StockDto::getMaterialId).collect(Collectors.toList());
//		List<Long> warehouseIds = stockDtos.stream().map(StockDto::getWarehouseId).collect(Collectors.toList());
		List<MaterialDto> materialDtos = mainDataServiceClient.getMaterialsByIds(materialIds).getBody();
//		List<WarehouseDto> warehouseDtos = mainDataServiceClient.getWarehousesByIds(warehouseIds).getBody();

		for (ConsumptionStockDto consumptionStockDto : consumptionStockDtos) {
			for (StockDto stockDto : stockDtos) {
				if (consumptionStockDto.getStockId() == stockDto.getStockId()) {
					consumptionStockDto.setQuantity(stockDto.getQuantity());
					consumptionStockDto.setMaterialId(stockDto.getMaterialId());
					consumptionStockDto.setWarehouseId(stockDto.getWarehouseId());
					break;
				}
			}
			for (MaterialDto materialDto : materialDtos) {
				if (materialDto.getMaterialId() == consumptionStockDto.getMaterialId()){
					consumptionStockDto.setMaterialDesc(materialDto.getMaterialDesc());
					consumptionStockDto.setMaterialNumber(materialDto.getMaterialNumber());
					break;
				}
			}
		}

		return consumptionStockDtos;
	}

}
