package com.oee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.oee.entity.PlantEntity;
import com.oee.repository.PlantRepository;
import com.oee.service.PlantInfoService;

@Service
@RequiredArgsConstructor
public class PlantInfoServiceImpl implements PlantInfoService{

	private final PlantRepository plantRepository;
	

	@Override
	public PlantEntity create(PlantEntity plantEntity) {
		plantRepository.save(plantEntity);
		return plantEntity;
	}


	@Override
	public PlantEntity update(PlantEntity plantEntity) {
		return plantRepository.save(plantEntity);
	}

	@Override
	public Boolean delete(Long plantId) {
		PlantEntity plantEntity = plantRepository.findById(plantId).get();
		return Boolean.TRUE;
	}

	@Override
	public PlantEntity getById(Long plantId) {
		return plantRepository.findById(plantId).get();
	}

}
