package com.oee.service;

import java.util.List;

import com.oee.entity.PlantInfo;

public interface PlantInfoService {
	
	PlantInfo create(PlantInfo plantInfo);
	
	PlantInfo update(PlantInfo plantInfo);
	
	Boolean delete(Integer plantId);
	
	PlantInfo getById(Integer plantId);
	
	List<PlantInfo> getByClientId(Integer clientId);
	
}
