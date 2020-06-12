package com.oee.service.impl;

import org.springframework.stereotype.Service;

import com.oee.entity.UserInfo;
import com.oee.repository.UserInfoRepository;
import com.oee.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	private final UserInfoRepository userInfoRepository;
	
	public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}
	
	@Override
	public UserInfo create(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}

	@Override
	public UserInfo update(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}

	@Override
	public Boolean delete(Long id) {
		userInfoRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public UserInfo getByUserInfoId(Long id) {
		return userInfoRepository.findById(id).get();
	}

	@Override
	public UserInfo getByUserId(Long id) {
		return userInfoRepository.findByUserUserId(id);
	}

}
