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
import com.oee.entity.Warehouse;
import com.oee.service.QualityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.oee.entity.Plant;
import com.oee.repository.PlantRepository;
import com.oee.service.PlantInfoService;

@Service
@RequiredArgsConstructor
public class PlantInfoServiceImpl implements PlantInfoService{

	private final PlantRepository plantRepository;
	

	@Override
	public Plant create(Plant plant) {
		plantRepository.save(plant);
		return plant;
	}


	@Override
	public Plant update(Plant plant) {
		return plantRepository.save(plant);
	}

	@Override
	public Boolean delete(Long plantId) {
		Plant plant = plantRepository.findById(plantId).get();
		return Boolean.TRUE;
	}

	@Override
	public Plant getById(Long plantId) {
		return plantRepository.findById(plantId).get();
	}

}
