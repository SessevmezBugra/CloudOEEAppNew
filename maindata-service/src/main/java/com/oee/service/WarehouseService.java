package com.oee.service;

import java.util.List;

import com.oee.entity.WarehouseInfo;

public interface WarehouseService {

	WarehouseInfo create(WarehouseInfo warehouseInfo);
	
	WarehouseInfo update(WarehouseInfo warehouseInfo);
	
	Boolean deleteById(Long warehouseId);
	
	WarehouseInfo getById(Long warehouseId);

	List<WarehouseInfo> getWarehouseByLoggedUser();

	List<WarehouseInfo> findByPlantId(Long plantId);
}
