package com.oee.service.impl;

import java.util.List;

import com.oee.client.AuthServiceClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.WarehouseEntity;
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
	public WarehouseEntity create(WarehouseEntity warehouseEntity) {
		return repository.save(warehouseEntity);
	}

	@Override
	public WarehouseEntity update(WarehouseEntity warehouseEntityInfo) {
		WarehouseEntity warehouseEntity = repository.findById(warehouseEntityInfo.getId()).get();
		warehouseEntity.setName(warehouseEntityInfo.getName());
		return repository.save(warehouseEntity);
	}

	@Override
	public Boolean deleteById(Long warehouseId) {
		repository.deleteById(warehouseId);
		return Boolean.TRUE;
	}

	@Override
	public WarehouseEntity getById(Long warehouseId) {
		return repository.findById(warehouseId).get();
	}

	@Override
	public List<WarehouseEntity> getByIds(List<Long> warehouseIds) {
		return repository.findAllById(warehouseIds);
	}

}
