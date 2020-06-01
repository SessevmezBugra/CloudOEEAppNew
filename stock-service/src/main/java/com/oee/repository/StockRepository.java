package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.StockInfo;

public interface StockRepository extends JpaRepository<StockInfo, Long>{
	
	List<StockInfo> findByWarehouseId(Long warehouseId);
	
}
