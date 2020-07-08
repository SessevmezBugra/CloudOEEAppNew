package com.oee.service.impl;

import org.springframework.stereotype.Service;

import com.oee.entity.OrderedMaterial;
import com.oee.repository.OrderedMaterialRepository;
import com.oee.service.OrderedMaterialService;

import java.util.List;

@Service
public class OrderedMaterialServiceImpl implements OrderedMaterialService{

	private final OrderedMaterialRepository orderedMaterialRepository;
	
	public OrderedMaterialServiceImpl(OrderedMaterialRepository orderedMaterialRepository) {
		this.orderedMaterialRepository = orderedMaterialRepository;
	}
	
	@Override
	public OrderedMaterial create(OrderedMaterial orderedMaterial) {
		return orderedMaterialRepository.save(orderedMaterial);
	}

	@Override
	public OrderedMaterial update(OrderedMaterial orderedMaterial) {
		return orderedMaterialRepository.save(orderedMaterial);
	}

	@Override
	public Boolean delete(Long id) {
		orderedMaterialRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public OrderedMaterial getById(Long id) {
		return orderedMaterialRepository.findById(id).get();
	}

	@Override
	public OrderedMaterial getByOrderId(Long id) {
		return orderedMaterialRepository.findByOrderOrderId(id);
	}

}
