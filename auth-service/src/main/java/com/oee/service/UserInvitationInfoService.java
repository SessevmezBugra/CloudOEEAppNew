package com.oee.service;

import com.oee.entity.UserInvitationInfo;

public interface UserInvitationInfoService {
	
	UserInvitationInfo create(UserInvitationInfo userInvitationInfo);
	
	UserInvitationInfo update(UserInvitationInfo userInvitationInfo);
	
	Boolean delete(Long id);
	
	UserInvitationInfo getById(Long id);
	
}
