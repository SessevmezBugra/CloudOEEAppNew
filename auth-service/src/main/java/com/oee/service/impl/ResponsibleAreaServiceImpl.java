package com.oee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oee.entity.ResponsibleArea;
import com.oee.repository.ResponsibleAreaRepository;
import com.oee.service.ResponsibleAreaService;

@Service
public class ResponsibleAreaServiceImpl implements ResponsibleAreaService{

	@Autowired
	private ResponsibleAreaRepository responsibleAreaRepository;
	
	@Override
	public ResponsibleArea create(ResponsibleArea responsibleArea) {
		return responsibleAreaRepository.save(responsibleArea);
	}

	@Override
	public ResponsibleArea update(ResponsibleArea responsibleArea) {
		return responsibleAreaRepository.save(responsibleArea);
	}

	@Override
	public Boolean delete(Long id) {
		responsibleAreaRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ResponsibleArea getById(Long id) {
		return responsibleAreaRepository.findById(id).get();
	}

	@Override
	public List<ResponsibleArea> getByUsername(String username) {
		return responsibleAreaRepository.findByUserUsername(username);
	}

}
