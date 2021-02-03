package com.oee.service.impl;

import java.util.List;

import com.oee.client.AuthServiceClient;
import com.oee.entity.MaterialEntity;
import org.springframework.stereotype.Service;

import com.oee.repository.MaterialRepository;
import com.oee.service.MaterialInfoService;

@Service
public class MaterialInfoServiceImpl implements MaterialInfoService{
	
	private final MaterialRepository materialRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	
	public MaterialInfoServiceImpl(MaterialRepository materialRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider) {
		this.materialRepository = materialRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public MaterialEntity create(MaterialEntity materialEntity) {
		return materialRepository.save(materialEntity);
	}

	@Override
	public MaterialEntity update(MaterialEntity materialEntityInfo) {
		MaterialEntity materialEntity = materialRepository.findById(materialEntityInfo.getId()).get();
		materialEntity.setDesc(materialEntityInfo.getDesc());
		materialEntity.setNumber(materialEntityInfo.getNumber());
		return materialRepository.save(materialEntity);
	}

	@Override
	public Boolean delete(Long materialId) {
		materialRepository.deleteById(materialId);
		return Boolean.TRUE;
	}

	@Override
	public MaterialEntity getById(Long materialId) {
		return materialRepository.findById(materialId).get();
	}

	@Override
	public List<MaterialEntity> getMaterialsByIds(List<Long> ids) {
		return materialRepository.findAllById(ids);
	}

}
