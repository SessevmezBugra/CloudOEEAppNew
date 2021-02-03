package com.oee.service;

import java.util.List;

import com.oee.entity.MaterialEntity;

public interface MaterialInfoService {
	
	MaterialEntity create(MaterialEntity materialEntity);
	
	MaterialEntity update(MaterialEntity materialEntity);
	
	Boolean delete(Long materialId);
	
	MaterialEntity getById(Long materialId);

    List<MaterialEntity> getMaterialsByIds(List<Long> ids);
}
