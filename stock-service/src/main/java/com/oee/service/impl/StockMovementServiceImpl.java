package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.StockMovement;
import com.oee.repository.StockMovementRepository;
import com.oee.service.StockMovementService;

@Service
public class StockMovementServiceImpl implements StockMovementService{
	
	private final StockMovementRepository stockMovementRepository;
	private final CurrentUserProvider currentUserProvider;
	
	public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, CurrentUserProvider currentUserProvider) {
		this.stockMovementRepository = stockMovementRepository;
		this.currentUserProvider = currentUserProvider;
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
	public List<StockMovement> getByWarehouseId(Long id) {
		return stockMovementRepository.findByStockWarehouseId(id);
	}

}
