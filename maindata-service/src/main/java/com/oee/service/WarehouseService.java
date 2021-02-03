package com.oee.service;

import java.util.List;

import com.oee.entity.WarehouseEntity;

public interface WarehouseService {

	WarehouseEntity create(WarehouseEntity warehouseEntity);
	
	WarehouseEntity update(WarehouseEntity warehouseEntity);
	
	Boolean deleteById(Long warehouseId);
	
	WarehouseEntity getById(Long warehouseId);

    List<WarehouseEntity> getByIds(List<Long> warehouseIds);
}
