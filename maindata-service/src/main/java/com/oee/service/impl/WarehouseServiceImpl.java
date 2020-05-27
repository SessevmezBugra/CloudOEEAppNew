package com.oee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oee.entity.WarehouseInfo;
import com.oee.repository.WarehouseRepository;
import com.oee.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseRepository repository;
	
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
	public List<WarehouseInfo> findByPlantId(Long plantId) {
		return repository.findByPlantPlantId(plantId);
	}

}
