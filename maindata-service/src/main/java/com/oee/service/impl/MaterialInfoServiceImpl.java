package com.oee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
import org.springframework.stereotype.Service;

import com.oee.entity.MaterialInfo;
import com.oee.repository.MaterialInfoRepository;
import com.oee.service.MaterialInfoService;

@Service
public class MaterialInfoServiceImpl implements MaterialInfoService{
	
	private final MaterialInfoRepository materialInfoRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	
	public MaterialInfoServiceImpl(MaterialInfoRepository materialInfoRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider) {
		this.materialInfoRepository = materialInfoRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public MaterialInfo create(MaterialInfo materialInfo) {
		return materialInfoRepository.save(materialInfo);
	}

	@Override
	public MaterialInfo update(MaterialInfo materialInfo) {
		MaterialInfo material = materialInfoRepository.findById(materialInfo.getMaterialId()).get();
		material.setMaterialDesc(materialInfo.getMaterialDesc());
		material.setMaterialNumber(materialInfo.getMaterialNumber());
		return materialInfoRepository.save(material);
	}

	@Override
	public Boolean delete(Long materialId) {
		materialInfoRepository.deleteById(materialId);
		return Boolean.TRUE;
	}

	@Override
	public MaterialInfo getById(Long materialId) {
		return materialInfoRepository.findById(materialId).get();
	}

	@Override
	public List<MaterialInfo> getByPlantId(Long plantId) {
		return materialInfoRepository.findByPlantPlantId(plantId);
	}

	@Override
	public List<MaterialInfo> getMaterialByLoggedUser() {
		CurrentUser currentUser = currentUserProvider.getCurrentUser();
		Boolean isCompanyOwner = currentUser.hasRole(UserRole.COMPANY_OWNER.getRole());
		Boolean isClientManager = currentUser.hasRole(UserRole.CLIENT_MANAGER.getRole());
		Boolean isPlantManager = currentUser.hasRole(UserRole.PLANT_MANAGER.getRole());
		Boolean isOperator = currentUser.hasRole(UserRole.OPERATOR.getRole());

		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.COMPANY)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			return materialInfoRepository.findByPlantClientCompanyCompanyIdIn(ids);
		}

		throw new RuntimeException("Herhangi bir bolge bulunamadi.");
	}

	@Override
	public List<MaterialInfo> getMaterialsByIds(List<Long> ids) {
		return materialInfoRepository.findAllById(ids);
	}

}
