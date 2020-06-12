package com.oee.service.impl;

import org.springframework.stereotype.Service;

import com.oee.entity.UserInvitationInfo;
import com.oee.repository.UserInvitationInfoRepository;
import com.oee.service.UserInvitationInfoService;

@Service
public class UserInvitationInfoServiceImpl implements UserInvitationInfoService{

	private final UserInvitationInfoRepository userInvitationInfoRepository;
	
	public UserInvitationInfoServiceImpl(UserInvitationInfoRepository userInvitationInfoRepository) {
		this.userInvitationInfoRepository = userInvitationInfoRepository;
	}
	
	@Override
	public UserInvitationInfo create(UserInvitationInfo userInvitationInfo) {
		return userInvitationInfoRepository.save(userInvitationInfo);
	}

	@Override
	public UserInvitationInfo update(UserInvitationInfo userInvitationInfo) {
		return userInvitationInfoRepository.save(userInvitationInfo);
	}

	@Override
	public Boolean delete(Long id) {
		userInvitationInfoRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public UserInvitationInfo getById(Long id) {
		return userInvitationInfoRepository.findById(id).get();
	}

}
