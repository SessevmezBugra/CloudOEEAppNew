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

import com.oee.entity.Warehouse;
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
	public Warehouse create(Warehouse warehouse) {
		return repository.save(warehouse);
	}

	@Override
	public Warehouse update(Warehouse warehouseInfo) {
		Warehouse warehouse = repository.findById(warehouseInfo.getId()).get();
		warehouse.setName(warehouseInfo.getName());
		return repository.save(warehouse);
	}

	@Override
	public Boolean deleteById(Long warehouseId) {
		repository.deleteById(warehouseId);
		return Boolean.TRUE;
	}

	@Override
	public Warehouse getById(Long warehouseId) {
		return repository.findById(warehouseId).get();
	}

	@Override
	public List<Warehouse> getByIds(List<Long> warehouseIds) {
		return repository.findAllById(warehouseIds);
	}

}
