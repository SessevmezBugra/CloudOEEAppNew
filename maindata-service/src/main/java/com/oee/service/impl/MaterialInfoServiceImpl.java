package com.oee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
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
	public List<MaterialInfo> getByPlantId(Long plantId) {
		return materialInfoRepository.findByPlantPlantId(plantId);
	}

	@Override
	public List<MaterialInfo> getMaterialByLoggedUser() {
		CurrentUser currentUser = currentUserProvider.getCurrentUser();
		Boolean isCompanyOwner = currentUser.hasRole("ROLE_COMPANY_OWNER");
		Boolean isClientManager = currentUser.hasRole("ROLE_CLIENT_MANAGER");
		Boolean isPlantManager = currentUser.hasRole("ROLE_PLANT_MANAGER");
		Boolean isOperator = currentUser.hasRole("ROLE_OPERATOR");

		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals("COMPANY")).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			return materialInfoRepository.findByPlantClientCompanyCompanyIdIn(ids);
		}

		throw new RuntimeException("Herhangi bir bolge bulunamadi.");
	}

	@Override
	public List<MaterialInfo> getMaterialsByIds(List<Long> ids) {
		return materialInfoRepository.findAllById(ids);
	}

}
