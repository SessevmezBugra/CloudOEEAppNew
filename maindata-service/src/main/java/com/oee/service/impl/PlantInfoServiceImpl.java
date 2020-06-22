package com.oee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import org.springframework.stereotype.Service;

import com.oee.entity.PlantInfo;
import com.oee.repository.PlantInfoRepository;
import com.oee.service.PlantInfoService;

@Service
public class PlantInfoServiceImpl implements PlantInfoService{

	private final PlantInfoRepository plantInfoRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	
	public PlantInfoServiceImpl(PlantInfoRepository plantInfoRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider) {
		this.plantInfoRepository = plantInfoRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
	}
	@Override
	public PlantInfo create(PlantInfo plantInfo) {
		return plantInfoRepository.save(plantInfo);
	}

	@Override
	public PlantInfo update(PlantInfo plantInfo) {
		return plantInfoRepository.save(plantInfo);
	}

	@Override
	public Boolean delete(Long plantId) {
		plantInfoRepository.deleteById(plantId);
		return Boolean.TRUE;
	}

	@Override
	public PlantInfo getById(Long plantId) {
		return plantInfoRepository.findById(plantId).get();
	}

	@Override
	public List<PlantInfo> getByClientId(Long clientId) {
		return plantInfoRepository.findByClientClientId(clientId);
	}

	@Override
	public List<PlantInfo> getPlantByLoggedUser() {
		CurrentUser currentUser = currentUserProvider.getCurrentUser();
		Boolean isCompanyOwner = currentUser.hasRole("ROLE_COMPANY_OWNER");
		Boolean isClientManager = currentUser.hasRole("ROLE_CLIENT_MANAGER");
		Boolean isPlantManager = currentUser.hasRole("ROLE_PLANT_MANAGER");
		Boolean isOperator = currentUser.hasRole("ROLE_OPERATOR");

		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals("COMPANY")).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			return plantInfoRepository.findByClientCompanyCompanyIdIn(ids);
		}

		throw new RuntimeException("Herhangi bir bolge bulunamadi.");
	}


}
