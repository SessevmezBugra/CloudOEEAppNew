package com.oee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import org.springframework.stereotype.Service;

import com.oee.entity.Material;
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
	public Material create(Material material) {
		return materialRepository.save(material);
	}

	@Override
	public Material update(Material materialInfo) {
		Material material = materialRepository.findById(materialInfo.getId()).get();
		material.setDesc(materialInfo.getDesc());
		material.setNumber(materialInfo.getNumber());
		return materialRepository.save(material);
	}

	@Override
	public Boolean delete(Long materialId) {
		materialRepository.deleteById(materialId);
		return Boolean.TRUE;
	}

	@Override
	public Material getById(Long materialId) {
		return materialRepository.findById(materialId).get();
	}

	@Override
	public List<Material> getMaterialsByIds(List<Long> ids) {
		return materialRepository.findAllById(ids);
	}

}
