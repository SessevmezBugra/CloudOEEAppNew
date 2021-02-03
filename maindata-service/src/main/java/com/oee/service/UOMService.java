package com.oee.service;

import java.util.List;

import com.oee.entity.UnitOfMeasureEntity;

public interface UOMService {
	
	UnitOfMeasureEntity create(UnitOfMeasureEntity unitOfMeasureEntity);
	
	UnitOfMeasureEntity update(UnitOfMeasureEntity unitOfMeasureEntity);
	
	Boolean delete(Integer id);
	
	UnitOfMeasureEntity getById(Integer id);
	
	List<UnitOfMeasureEntity> getAll();
}
