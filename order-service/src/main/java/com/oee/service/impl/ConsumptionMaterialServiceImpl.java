package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.ConsumptionMaterial;
import com.oee.repository.ConsumptionMaterialRepository;
import com.oee.service.ConsumptionMaterialService;

@Service
public class ConsumptionMaterialServiceImpl implements ConsumptionMaterialService{

	private final ConsumptionMaterialRepository consumptionMaterialRepository;
	
	public ConsumptionMaterialServiceImpl(ConsumptionMaterialRepository consumptionMaterialRepository) {
		this.consumptionMaterialRepository = consumptionMaterialRepository;
	}
	
	@Override
	public ConsumptionMaterial create(ConsumptionMaterial consumptionMaterial) {
		return consumptionMaterialRepository.save(consumptionMaterial);
	}

	@Override
	public ConsumptionMaterial update(ConsumptionMaterial consumptionMaterial) {
		return consumptionMaterialRepository.save(consumptionMaterial);
	}

	@Override
	public Boolean delete(Long id) {
		consumptionMaterialRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ConsumptionMaterial getById(Long id) {
		return consumptionMaterialRepository.findById(id).get();
	}

	@Override
	public List<ConsumptionMaterial> getByOrderId(Long id) {
		return consumptionMaterialRepository.findByOrderOrderId(id);
	}

}
