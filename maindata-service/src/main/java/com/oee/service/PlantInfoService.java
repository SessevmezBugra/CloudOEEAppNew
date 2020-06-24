package com.oee.service;

import java.util.List;

import com.oee.entity.PlantInfo;

public interface PlantInfoService {
	
	PlantInfo create(PlantInfo plantInfo);
	
	PlantInfo update(PlantInfo plantInfo);
	
	Boolean delete(Long plantId);
	
	PlantInfo getById(Long plantId);
	
	List<PlantInfo> getByClientId(Long clientId);

	List<PlantInfo> getPlantByLoggedUser();

    PlantInfo getByWarehouseId(Long warehouseId);
}
