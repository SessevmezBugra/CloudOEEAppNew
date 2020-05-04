package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.PlantInfo;
import com.oee.repository.PlantInfoRepository;
import com.oee.service.PlantInfoService;

@Service
public class PlantInfoServiceImpl implements PlantInfoService{

	private final PlantInfoRepository plantInfoRepository;
	
	public PlantInfoServiceImpl(PlantInfoRepository plantInfoRepository) {
		this.plantInfoRepository = plantInfoRepository;
	}
	@Override
	public PlantInfo create(PlantInfo plantInfo) {
		return plantInfoRepository.save(plantInfo);
	}

	@Override
	public PlantInfo update(PlantInfo plantInfo) {
		return plantInfoRepository.save(plantInfo);
	}

	@Override
	public Boolean delete(Integer plantId) {
		plantInfoRepository.deleteById(plantId);
		return Boolean.TRUE;
	}

	@Override
	public PlantInfo getById(Integer plantId) {
		return plantInfoRepository.getOne(plantId);
	}

	@Override
	public List<PlantInfo> getByClientId(Integer clientId) {
		return plantInfoRepository.findByClientClientId(clientId);
	}

}
