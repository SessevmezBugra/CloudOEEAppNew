package com.oee.service;

import java.util.List;

import com.oee.entity.ConsumptionMaterial;

public interface ConsumptionMaterialService {
	
	ConsumptionMaterial create(ConsumptionMaterial consumptionMaterial);
	
	ConsumptionMaterial update(ConsumptionMaterial consumptionMaterial);
	
	Boolean delete(Long id);
	
	ConsumptionMaterial getById(Long id);
	
	List<ConsumptionMaterial> getByOrderId(Long id);

}
