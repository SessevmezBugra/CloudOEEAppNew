package com.oee.service;

import java.util.List;

import com.oee.dto.WarehouseDto;
import com.oee.entity.Warehouse;

public interface WarehouseService {

	Warehouse create(Warehouse warehouse);
	
	Warehouse update(Warehouse warehouse);
	
	Boolean deleteById(Long warehouseId);
	
	Warehouse getById(Long warehouseId);

    List<Warehouse> getByIds(List<Long> warehouseIds);
}
