package com.oee.service;

import java.util.List;

import com.oee.dto.StockMovDto;
import com.oee.entity.StockMovement;

public interface StockMovementService {

	StockMovement create(StockMovement stockMovement);

	List<StockMovement> createAll(List<StockMovement> stockMovements);
	
	StockMovement update(StockMovement stockMovement);
	
	Boolean delete(Long stockMovementId);
	
	StockMovement getById(Long stockMovId);
	
	List<StockMovement> getByStockId(Long stockId);

    List<StockMovDto> getByWarehouseId(Long id);
}
