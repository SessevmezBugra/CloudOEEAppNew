package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.client.OrderServiceClient;
import com.oee.client.StockServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.entity.QualityType;
import com.oee.entity.WarehouseInfo;
import com.oee.enums.AreaType;
import com.oee.enums.QualityTypeEnum;
import com.oee.enums.UserRole;
import com.oee.service.QualityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.oee.entity.PlantInfo;
import com.oee.repository.PlantInfoRepository;
import com.oee.service.PlantInfoService;

@Service
@RequiredArgsConstructor
public class PlantInfoServiceImpl implements PlantInfoService{

	private final PlantInfoRepository plantInfoRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	private final StockServiceClient stockServiceClient;
	private final OrderServiceClient orderServiceClient;
	private final QualityTypeService qualityTypeService;
	

	@Override
	public PlantInfo create(PlantInfo plantInfo) {
		plantInfoRepository.save(plantInfo);
		createStandartQualities(plantInfo);
		return plantInfo;
	}

	private void createStandartQualities(PlantInfo plantInfo) {
		QualityType qualityType = new QualityType();
		qualityType.setPlant(plantInfo);
		qualityType.setQualityType(QualityTypeEnum.FIRST_QUALITY.getQualityType());
		qualityType.setQualityDesc(QualityTypeEnum.FIRST_QUALITY.getQualityDesc());
		qualityTypeService.create(qualityType);
		qualityType = new QualityType();
		qualityType.setPlant(plantInfo);
		qualityType.setQualityType(QualityTypeEnum.SECOND_QUALITY.getQualityType());
		qualityType.setQualityDesc(QualityTypeEnum.SECOND_QUALITY.getQualityDesc());
		qualityTypeService.create(qualityType);
		qualityType = new QualityType();
		qualityType.setPlant(plantInfo);
		qualityType.setQualityType(QualityTypeEnum.SCRAP.getQualityType());
		qualityType.setQualityDesc(QualityTypeEnum.SCRAP.getQualityDesc());
		qualityTypeService.create(qualityType);
	}

	@Override
	public PlantInfo update(PlantInfo plantInfo) {
		return plantInfoRepository.save(plantInfo);
	}

	@Override
	public Boolean delete(Long plantId) {
		PlantInfo plantInfo = plantInfoRepository.findById(plantId).get();
		List<Long> warehouseIds = new ArrayList<>();
		List<Long> plantIds = new ArrayList<>();
		plantIds.add(plantInfo.getPlantId());
			List<WarehouseInfo> warehouses = plantInfo.getWarehouses();
			for (WarehouseInfo warehouse : warehouses) {
				warehouseIds.add(warehouse.getWarehouseId());
			}
		plantInfoRepository.deleteById(plantId);
		orderServiceClient.deleteOrderByPlantIds(plantIds);
		stockServiceClient.deleteStockByWarehouseIds(warehouseIds);
		authServiceClient.deleteResponsibleAreaByIds(plantIds);
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
		Boolean isOperator = currentUser.hasRole(UserRole.OPERATOR.getRole());
		List<PlantInfo> plantInfos = new ArrayList<>();
		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.COMPANY)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findByClientCompanyCompanyIdIn(ids);
			return  plantInfos;
		}else if(isClientManager) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.CLIENT)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findByClientClientIdIn(ids);
			return  plantInfos;
		}else if(isPlantManager) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.PLANT)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findAllById(ids);
			return  plantInfos;
		}else if(isOperator) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.PLANT)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			plantInfos = plantInfoRepository.findAllById(ids);
			return  plantInfos;
		}
		return  plantInfos;
	}

	@Override
	public PlantInfo getByWarehouseId(Long warehouseId) {
		return plantInfoRepository.findByWarehousesWarehouseId(warehouseId);
	}

	@Override
	public List<PlantInfo> getPlantByIds(List<Long> ids) {
		return plantInfoRepository.findAllById(ids);
	}

}
