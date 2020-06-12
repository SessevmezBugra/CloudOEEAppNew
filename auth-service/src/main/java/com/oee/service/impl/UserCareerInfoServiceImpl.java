package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.UserCareerInfo;
import com.oee.repository.UserCareerInfoRepository;
import com.oee.service.UserCareerInfoService;

@Service
public class UserCareerInfoServiceImpl implements UserCareerInfoService{

	private final UserCareerInfoRepository careerInfoRepository;
	
	public UserCareerInfoServiceImpl(UserCareerInfoRepository careerInfoRepository) {
		this.careerInfoRepository = careerInfoRepository;
	}
	
	@Override
	public UserCareerInfo create(UserCareerInfo userCareerInfo) {
		return careerInfoRepository.save(userCareerInfo);
	}

	@Override
	public UserCareerInfo update(UserCareerInfo userCareerInfo) {
		return careerInfoRepository.save(userCareerInfo);
	}

	@Override
	public Boolean delete(Long id) {
		careerInfoRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public UserCareerInfo getById(Long id) {
		return careerInfoRepository.findById(id).get();
	}

	@Override
	public List<UserCareerInfo> getByUserId(Long userId) {
		return careerInfoRepository.findByUserUserId(userId);
	}

}
