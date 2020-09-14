package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.StockMovement;
import com.oee.repository.StockMovementRepository;
import com.oee.service.StockMovementService;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService{
	
	private final StockMovementRepository stockMovementRepository;
	private final CurrentUserProvider currentUserProvider;
	private final MainDataServiceClient mainDataServiceClient;
	private final ModelMapper modelMapper;

	@Override
	public StockMovement create(StockMovement stockMovement) {
		stockMovement.setUsername(currentUserProvider.getCurrentUser().getUsername());
		return stockMovementRepository.save(stockMovement);
	}

	@Override
	public List<StockMovement> createAll(List<StockMovement> stockMovements) {
		return stockMovementRepository.saveAll(stockMovements);
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
		List<MaterialDto> materialDtos = plantDto.getMaterials();
		List<WarehouseDto> warehouseDtos = plantDto.getWarehouses();
		List<StockMovDto> stockMovDtos = Arrays.asList(modelMapper.map(stockMovementRepository.findByStockWarehouseId(id), StockMovDto[].class));
		for (StockMovDto stockMovDto : stockMovDtos) {
			for (MaterialDto materialDto : materialDtos) {
				if(stockMovDto.getStock().getMaterialId() == materialDto.getMaterialId()) {
					stockMovDto.getStock().setMaterialDesc(materialDto.getMaterialDesc());
					stockMovDto.getStock().setMaterialNumber(materialDto.getMaterialNumber());
					break;
				}
			}
			for (WarehouseDto warehouseDto : warehouseDtos) {
				if(stockMovDto.getStock().getWarehouseId() == warehouseDto.getWarehouseId()) {
					stockMovDto.getStock().setWarehouseName(warehouseDto.getWarehouseName());
					break;
				}
			}
		}
		return stockMovDtos;
	}

}
