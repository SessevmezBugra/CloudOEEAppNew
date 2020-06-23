package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.dto.WarehouseDto;
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
		return repository.save(warehouseInfo);
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
		Boolean isCompanyOwner = currentUser.hasRole("ROLE_COMPANY_OWNER");
		Boolean isClientManager = currentUser.hasRole("ROLE_CLIENT_MANAGER");
		Boolean isPlantManager = currentUser.hasRole("ROLE_PLANT_MANAGER");
		Boolean isOperator = currentUser.hasRole("ROLE_OPERATOR");

		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals("COMPANY")).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			return Arrays.asList(modelMapper.map(repository.findByPlantClientCompanyCompanyIdIn(ids), WarehouseDto[].class));
		}

		throw new RuntimeException("Herhangi bir bolge bulunamadi.");

	}

	@Override
	public List<WarehouseInfo> findByPlantId(Long plantId) {
		return repository.findByPlantPlantId(plantId);
	}

}
