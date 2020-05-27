package com.oee.service;

import java.util.List;

import com.oee.entity.MaterialInfo;

public interface MaterialInfoService {
	
	MaterialInfo create(MaterialInfo materialInfo);
	
	MaterialInfo update(MaterialInfo materialInfo);
	
	Boolean delete(Long materialId);
	
	MaterialInfo getById(Long materialId);
	
	List<MaterialInfo> getByPlantId(Long plantId);
	
}
