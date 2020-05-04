package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.UnitOfMeasures;
import com.oee.repository.UOMRepository;
import com.oee.service.UOMService;

@Service
public class UOMServiceImpl implements UOMService{
	
	private final UOMRepository uomRepository;
	
	public UOMServiceImpl(UOMRepository uomRepository) {
		this.uomRepository = uomRepository;
	}

	@Override
	public UnitOfMeasures create(UnitOfMeasures unitOfMeasures) {
		return uomRepository.save(unitOfMeasures);
	}

	@Override
	public UnitOfMeasures update(UnitOfMeasures unitOfMeasures) {
		return uomRepository.save(unitOfMeasures);
	}

	@Override
	public Boolean delete(Integer id) {
		uomRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public UnitOfMeasures getById(Integer id) {
		return uomRepository.getOne(id);
	}

	@Override
	public List<UnitOfMeasures> getAll() {
		return uomRepository.findAll();
	}

}
