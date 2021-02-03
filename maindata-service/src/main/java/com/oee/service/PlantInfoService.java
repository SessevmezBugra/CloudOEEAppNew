package com.oee.service;

import com.oee.entity.PlantEntity;

public interface PlantInfoService {
	
	PlantEntity create(PlantEntity plantEntity);
	
	PlantEntity update(PlantEntity plantEntity);
	
	Boolean delete(Long plantId);
	
	PlantEntity getById(Long plantId);

}
