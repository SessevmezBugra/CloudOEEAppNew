package com.oee.service;

import java.util.List;

import com.oee.dto.StockDto;
import com.oee.entity.StockInfo;

public interface StockInfoService {
	
	StockInfo create(StockInfo stockInfo);
	
	StockInfo update(StockInfo stockInfo);
	
	Boolean delete(Long id);
	
	StockInfo getById(Long id);

	List<StockInfo> getByIds(List<Long> ids);

	List<StockDto> getByWarehouseId(Long warehouseId);

	Boolean deleteByWarehouseIds(List<Long> warehouseIds);
	
	StockInfo addStock(StockInfo stockInfo);
	
	StockInfo extractStock(StockInfo stockInfo);

    StockInfo getByWarehouseIdAndMaterialId(Long warehouseId, Long materialId);

    List<StockDto> getByPlantId(Long plantId);

    List<StockInfo> extractAllStock(List<StockInfo> stockInfos);
}
