package com.oee.service;

import java.util.List;

import com.oee.entity.Plant;

public interface PlantInfoService {
	
	Plant create(Plant plant);
	
	Plant update(Plant plant);
	
	Boolean delete(Long plantId);
	
	Plant getById(Long plantId);

}
