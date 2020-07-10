package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
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
		Boolean isCompanyOwner = currentUser.hasRole(UserRole.COMPANY_OWNER.getRole());
		Boolean isClientManager = currentUser.hasRole(UserRole.CLIENT_MANAGER.getRole());
		Boolean isPlantManager = currentUser.hasRole(UserRole.PLANT_MANAGER.getRole());
		List<PlantInfo> plantInfos = new ArrayList<>();
		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.COMPANY.name())).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findByClientCompanyCompanyIdIn(ids);
			return  plantInfos;
		}else if(isClientManager) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.CLIENT.name())).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findByClientClientIdIn(ids);
			return  plantInfos;
		}else if(isPlantManager) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.PLANT.name())).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findAllById(ids);
			return  plantInfos;
		}
		return  plantInfos;
	}

	@Override
	public PlantInfo getByWarehouseId(Long warehouseId) {
		return plantInfoRepository.findByWarehousesWarehouseId(warehouseId);
	}


}
