package com.oee.service;

import com.oee.entity.OrderedMaterial;

public interface OrderedMaterialService {
	
	OrderedMaterial create(OrderedMaterial orderedMaterial);
	
	OrderedMaterial update(OrderedMaterial orderedMaterial);
	
	Boolean delete(Long id);
	
	OrderedMaterial getById(Long id);
	
	OrderedMaterial getByOrderId(Long id);
}
