package com.oee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.oee.entity.PlantEntity;
import com.oee.repository.PlantRepository;
import com.oee.service.PlantService;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

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
	public void deleteById(Long plantId) {
		plantRepository.deleteById(plantId);
	}

	@Override
	public PlantEntity findById(Long plantId) {
		return plantRepository.findById(plantId).get();
	}

}
