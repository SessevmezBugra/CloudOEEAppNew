package com.oee.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.oee.entity.UnitOfMeasureEntity;
import com.oee.repository.UOMRepository;
import com.oee.service.UOMService;

@Service
@RequiredArgsConstructor
public class UOMServiceImpl implements UOMService{
	
	private final UOMRepository uomRepository;

	@Override
	public UnitOfMeasureEntity create(UnitOfMeasureEntity unitOfMeasureEntity) {
		return uomRepository.save(unitOfMeasureEntity);
	}

	@Override
	public UnitOfMeasureEntity update(UnitOfMeasureEntity unitOfMeasureEntity) {
		return uomRepository.save(unitOfMeasureEntity);
	}

	@Override
	public void deleteById(Long id) {
		uomRepository.deleteById(id);
	}

	@Override
	public UnitOfMeasureEntity findById(Long id) {
		return uomRepository.findById(id).get();
	}

	@Override
	public List<UnitOfMeasureEntity> findAll() {
		return uomRepository.findAll();
	}

}
