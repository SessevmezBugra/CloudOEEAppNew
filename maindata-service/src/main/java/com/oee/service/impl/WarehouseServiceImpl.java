package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.dto.WarehouseDto;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.WarehouseInfo;
import com.oee.repository.WarehouseRepository;
import com.oee.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	private final WarehouseRepository repository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	private final ModelMapper modelMapper;

	public WarehouseServiceImpl(WarehouseRepository repository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider, ModelMapper modelMapper) {
		this.repository = repository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
		this.modelMapper = modelMapper;
	}

	@Override
	public WarehouseInfo create(WarehouseInfo warehouseInfo) {
		return repository.save(warehouseInfo);
	}

	@Override
	public WarehouseInfo update(WarehouseInfo warehouseInfo) {
		WarehouseInfo warehouse = repository.findById(warehouseInfo.getWarehouseId()).get();
		warehouse.setWarehouseName(warehouseInfo.getWarehouseName());
		return repository.save(warehouse);
	}

	@Override
	public Boolean deleteById(Long warehouseId) {
		repository.deleteById(warehouseId);
		return Boolean.TRUE;
	}

	@Override
	public WarehouseInfo getById(Long warehouseId) {
		return repository.findById(warehouseId).get();
	}

	@Override
	public List<WarehouseDto> getWarehouseByLoggedUser() {
		CurrentUser currentUser = currentUserProvider.getCurrentUser();
		Boolean isCompanyOwner = currentUser.hasRole(UserRole.COMPANY_OWNER.getRole());
		Boolean isClientManager = currentUser.hasRole(UserRole.CLIENT_MANAGER.getRole());
		Boolean isPlantManager = currentUser.hasRole(UserRole.PLANT_MANAGER.getRole());
		Boolean isOperator = currentUser.hasRole(UserRole.OPERATOR.getRole());

		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.COMPANY)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			return Arrays.asList(modelMapper.map(repository.findByPlantClientCompanyCompanyIdIn(ids), WarehouseDto[].class));
		}

		throw new RuntimeException("Herhangi bir bolge bulunamadi.");

	}

	@Override
	public List<WarehouseInfo> findByPlantId(Long plantId) {
		return repository.findByPlantPlantId(plantId);
	}

	@Override
	public List<WarehouseInfo> getByIds(List<Long> warehouseIds) {
		return repository.findAllById(warehouseIds);
	}

}
