package com.oee.service;

import java.util.List;

import com.oee.entity.UnitOfMeasureEntity;
import com.oee.service.common.generic.GenericService;

public interface UOMService extends GenericService<UnitOfMeasureEntity, Long> {
	
	List<UnitOfMeasureEntity> findAll();
}
