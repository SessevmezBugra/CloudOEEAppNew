package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.UnitOfMeasureEntity;
import com.oee.repository.UOMRepository;
import com.oee.service.UOMService;

@Service
public class UOMServiceImpl implements UOMService{
	
	private final UOMRepository uomRepository;
	
	public UOMServiceImpl(UOMRepository uomRepository) {
		this.uomRepository = uomRepository;
	}

	@Override
	public UnitOfMeasureEntity create(UnitOfMeasureEntity unitOfMeasureEntity) {
		return uomRepository.save(unitOfMeasureEntity);
	}

	@Override
	public UnitOfMeasureEntity update(UnitOfMeasureEntity unitOfMeasureEntity) {
		return uomRepository.save(unitOfMeasureEntity);
	}

	@Override
	public Boolean delete(Integer id) {
		uomRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public UnitOfMeasureEntity getById(Integer id) {
		return uomRepository.getOne(id);
	}

	@Override
	public List<UnitOfMeasureEntity> getAll() {
		return uomRepository.findAll();
	}

}
