package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.dto.PlantDto;
import com.oee.dto.StockMovDto;
import com.oee.dto.WarehouseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.StockMovement;
import com.oee.repository.StockMovementRepository;
import com.oee.service.StockMovementService;

@Service
public class StockMovementServiceImpl implements StockMovementService{
	
	private final StockMovementRepository stockMovementRepository;
	private final CurrentUserProvider currentUserProvider;
	private final MainDataServiceClient mainDataServiceClient;
	private final ModelMapper modelMapper;
	
	public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, CurrentUserProvider currentUserProvider, MainDataServiceClient mainDataServiceClient, ModelMapper modelMapper) {
		this.stockMovementRepository = stockMovementRepository;
		this.currentUserProvider = currentUserProvider;
		this.mainDataServiceClient = mainDataServiceClient;
		this.modelMapper = modelMapper;
	}

	@Override
	public StockMovement create(StockMovement stockMovement) {
		stockMovement.setUsername(currentUserProvider.getCurrentUser().getUsername());
		return stockMovementRepository.save(stockMovement);
	}

	@Override
	public StockMovement update(StockMovement stockMovement) {
		return stockMovementRepository.save(stockMovement);
	}

	@Override
	public Boolean delete(Long stockMovementId) {
		stockMovementRepository.deleteById(stockMovementId);
		return Boolean.TRUE;
	}

	@Override
	public StockMovement getById(Long stockMovId) {
		return stockMovementRepository.findById(stockMovId).get();
	}

	@Override
	public List<StockMovement> getByStockId(Long stockId) {
		return stockMovementRepository.findByStockStockId(stockId);
	}

	@Override
	public List<StockMovDto> getByWarehouseId(Long id) {
		PlantDto plantDto = mainDataServiceClient.getPlantByWarehouseId(id).getBody();
		List<StockMovDto> stockMovDtos = Arrays.asList(modelMapper.map(stockMovementRepository.findByStockWarehouseId(id), StockMovDto[].class));
		for (int i = 0; i < stockMovDtos.size(); i++) {
			for (int k = 0; k < plantDto.getMaterials().size(); k++) {
				if(stockMovDtos.get(i).getMaterialId() == plantDto.getMaterials().get(k).getMaterialId()) {
					stockMovDtos.get(i).setMaterialDesc(plantDto.getMaterials().get(k).getMaterialDesc());
					stockMovDtos.get(i).setMaterialNumber(plantDto.getMaterials().get(k).getMaterialNumber());
				}
			}
			for (int l = 0; l < plantDto.getWarehouses().size(); l++) {
				if(stockMovDtos.get(i).getWarehouseId() == plantDto.getWarehouses().get(l).getWarehouseId()) {
					stockMovDtos.get(i).setWarehouseName(plantDto.getWarehouses().get(l).getWarehouseName());
				}
			}
		}
		return stockMovDtos;
	}

}
