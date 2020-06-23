package com.oee.service;

import java.util.List;

import com.oee.entity.StockInfo;

public interface StockInfoService {
	
	StockInfo create(StockInfo stockInfo);
	
	StockInfo update(StockInfo stockInfo);
	
	Boolean delete(Long id);
	
	StockInfo getById(Long id);

	List<StockInfo> getByWarehouseId(Long warehouseId);
	
	StockInfo addStock(StockInfo stockInfo);
	
	StockInfo extractStock(StockInfo stockInfo);

    StockInfo getByWarehouseIdAndMaterialId(Long warehouseId, Long materialId);
}
