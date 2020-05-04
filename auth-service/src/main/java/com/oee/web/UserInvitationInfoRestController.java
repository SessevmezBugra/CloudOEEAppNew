package com.oee.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.UserInvitationInfo;
import com.oee.service.UserInvitationInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.UserInvitationInfoCtrl.CTRL)
public class UserInvitationInfoRestController {

	private final UserInvitationInfoService userInvitationInfoService;
	
	public UserInvitationInfoRestController(UserInvitationInfoService userInvitationInfoService) {
		this.userInvitationInfoService = userInvitationInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserInvitationInfo> createUserInvitationInfo(@RequestBody UserInvitationInfo userInvitationInfo){
		return ResponseEntity.ok(userInvitationInfoService.create(userInvitationInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<UserInvitationInfo> updateUserInvitationInfo(@RequestBody UserInvitationInfo userInvitationInfo){
		return ResponseEntity.ok(userInvitationInfoService.update(userInvitationInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUserInvitationInfo(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userInvitationInfoService.delete(id));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserInvitationInfo> getUserInvitationInfo(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userInvitationInfoService.getById(id));
	}
}
