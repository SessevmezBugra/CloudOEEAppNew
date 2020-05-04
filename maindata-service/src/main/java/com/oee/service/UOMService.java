package com.oee.service;

import java.util.List;

import com.oee.entity.UnitOfMeasures;

public interface UOMService {
	
	UnitOfMeasures create(UnitOfMeasures unitOfMeasures);
	
	UnitOfMeasures update(UnitOfMeasures unitOfMeasures);
	
	Boolean delete(Integer id);
	
	UnitOfMeasures getById(Integer id);
	
	List<UnitOfMeasures> getAll();
}
