package com.oee.service;

import java.util.List;

import com.oee.dto.StockDto;
import com.oee.entity.Stock;

public interface StockInfoService {
	
	Stock create(Stock stock);
	
	Stock update(Stock stock);
	
	Boolean delete(Long id);
	
	Stock getById(Long id);

	List<Stock> getByIds(List<Long> ids);

	List<StockDto> getByWarehouseId(Long warehouseId);

	Boolean deleteByWarehouseIds(List<Long> warehouseIds);
	
	Stock addStock(Stock stock);
	
	Stock extractStock(Stock stock);

    Stock getByWarehouseIdAndMaterialId(Long warehouseId, Long materialId);

    List<StockDto> getByPlantId(Long plantId);

    List<Stock> extractAllStock(List<Stock> stocks);
}
