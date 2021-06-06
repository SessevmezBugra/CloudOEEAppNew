package com.oee.service.impl;

import com.oee.config.CurrentUserProvider;
import com.oee.entity.PlantEntity;
import com.oee.repository.PlantRepository;
import com.oee.service.NodeService;
import com.oee.service.PlantService;
import com.oee.util.builder.PlantBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

	private final PlantRepository plantRepository;
	private final NodeService nodeService;
	private final CurrentUserProvider currentUserProvider;

	@Override
	public PlantEntity create(PlantEntity plantEntity) {
		PlantEntity refPlant = new PlantBuilder().name(plantEntity.getName()).build();
		plantRepository.save(refPlant);
		return refPlant;
	}

	@Override
	public PlantEntity createWithSourcePlant(PlantEntity plantEntity) {

		PlantEntity sourcePlant = new PlantBuilder()
				.name(plantEntity.getName())
				.addUser(currentUserProvider.getCurrentUser().getUsername())
				.build();
		plantRepository.save(sourcePlant);

		PlantEntity refPlant = new PlantBuilder().name(sourcePlant.getName()).sourcePlant(sourcePlant).build();
		plantRepository.save(refPlant);

		return refPlant;
	}

	@Override
	public PlantEntity updateSourcePlant(Long refPlantId, Long sourcePlantId) {

		PlantEntity refPlant = findById(refPlantId);
		PlantEntity sourcePlant = findById(sourcePlantId);
		refPlant.setSourcePlant(sourcePlant);

		plantRepository.save(refPlant);

		return refPlant;
	}


	@Override
	public PlantEntity update(PlantEntity plantEntity) {
		return plantRepository.save(plantEntity);
	}

	@Override
	public void deleteById(Long plantId) {
		plantRepository.deleteById(plantId);
	}

	@Cacheable(value = "plant::plant-id", key = "#plantId")
	@Override
	public PlantEntity findById(Long plantId) {
		System.out.println(plantId);
		return plantRepository.findById(plantId).get();
	}

	@Override
	public List<PlantEntity> findAll() {
		return plantRepository.findAll();
	}
}
