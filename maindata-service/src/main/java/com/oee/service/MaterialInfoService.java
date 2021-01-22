package com.oee.service;

import java.util.List;

import com.oee.entity.Material;

public interface MaterialInfoService {
	
	Material create(Material material);
	
	Material update(Material material);
	
	Boolean delete(Long materialId);
	
	Material getById(Long materialId);

    List<Material> getMaterialsByIds(List<Long> ids);
}
