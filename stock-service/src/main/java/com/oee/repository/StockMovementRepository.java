package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long>{
	
	List<StockMovement> findByStockStockId(Long id);
	
}
