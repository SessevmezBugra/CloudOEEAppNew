package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.MaterialInfo;
import com.oee.repository.MaterialInfoRepository;
import com.oee.service.MaterialInfoService;

@Service
public class MaterialInfoServiceImpl implements MaterialInfoService{
	
	private final MaterialInfoRepository materialInfoRepository;
	
	public MaterialInfoServiceImpl(MaterialInfoRepository materialInfoRepository) {
		this.materialInfoRepository = materialInfoRepository;
	}

	@Override
	public MaterialInfo create(MaterialInfo materialInfo) {
		return materialInfoRepository.save(materialInfo);
	}

	@Override
	public MaterialInfo update(MaterialInfo materialInfo) {
		return materialInfoRepository.save(materialInfo);
	}

	@Override
	public Boolean delete(Long materialId) {
		materialInfoRepository.deleteById(materialId);
		return Boolean.TRUE;
	}

	@Override
	public MaterialInfo getById(Long materialId) {
		return materialInfoRepository.getOne(materialId);
	}

	@Override
	public List<MaterialInfo> getByPlantId(Integer plantId) {
		return materialInfoRepository.findByPlantPlantId(plantId);
	}

}
