package com.oee.service.impl;

import com.oee.client.AuthServiceClient;
import com.oee.config.CurrentUserProvider;
import com.oee.entity.WarehouseEntity;
import com.oee.repository.WarehouseRepository;
import com.oee.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

	private final WarehouseRepository repository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	private final ModelMapper modelMapper;

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
	public void deleteById(Long warehouseId) {
		repository.deleteById(warehouseId);
	}

	@Override
	public WarehouseEntity findById(Long warehouseId) {
		return repository.findById(warehouseId).get();
	}

	@Override
	public List<WarehouseEntity> findByIds(List<Long> warehouseIds) {
		return repository.findAllById(warehouseIds);
	}

}
