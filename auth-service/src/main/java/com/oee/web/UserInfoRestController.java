package com.oee.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.UserInfo;
import com.oee.service.UserInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.UserInfoCtrl.CTRL)
public class UserInfoRestController {

	private final UserInfoService userInfoService;
	
	public UserInfoRestController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo){
		return ResponseEntity.ok(userInfoService.create(userInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo){
		return ResponseEntity.ok(userInfoService.update(userInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUserInfo(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userInfoService.delete(id));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserInfo> getUserInfoById(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userInfoService.getByUserInfoId(id));
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable(value="id", required=true) Long userId){
		return ResponseEntity.ok(userInfoService.getByUserId(userId));
	}
	
}
