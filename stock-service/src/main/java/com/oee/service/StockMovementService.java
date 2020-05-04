package com.oee.service;

import java.util.List;

import com.oee.entity.StockMovement;

public interface StockMovementService {

	StockMovement create(StockMovement stockMovement);
	
	StockMovement update(StockMovement stockMovement);
	
	Boolean delete(Long stockMovementId);
	
	StockMovement getById(Long stockMovId);
	
	List<StockMovement> getByStockId(Long stockId);
}
