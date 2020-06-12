package com.oee.service;

import java.util.List;

import com.oee.entity.UserCareerInfo;

public interface UserCareerInfoService {
	
	UserCareerInfo create(UserCareerInfo userCareerInfo);
	
	UserCareerInfo update(UserCareerInfo userCareerInfo);
	
	Boolean delete(Long id);
	
	UserCareerInfo getById(Long id);
	
	List<UserCareerInfo> getByUserId(Long userId);
}
