package com.oee.service;

import com.oee.entity.UserInfo;

public interface UserInfoService {
	
	UserInfo create(UserInfo userInfo);
	
	UserInfo update(UserInfo userInfo);
	
	Boolean delete(Long id);
	
	UserInfo getByUserInfoId(Long id);
	
	UserInfo getByUserId(Long id);
	
}
