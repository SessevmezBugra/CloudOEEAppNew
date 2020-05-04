package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.UserCareerInfo;
import com.oee.service.UserCareerInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.UserCareerInfoCtrl.CTRL)
public class UserCareerInfoRestController {

	private final UserCareerInfoService userCareerInfoService;
	
	public UserCareerInfoRestController(UserCareerInfoService userCareerInfoService) {
		this.userCareerInfoService = userCareerInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserCareerInfo> createUserCareerInfo(@RequestBody UserCareerInfo userCareerInfo){
		return ResponseEntity.ok(userCareerInfoService.create(userCareerInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<UserCareerInfo> updateUserCareerInfo(@RequestBody UserCareerInfo userCareerInfo){
		return ResponseEntity.ok(userCareerInfoService.update(userCareerInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUserCareerInfo(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userCareerInfoService.delete(id));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserCareerInfo> getUserCareerInfo(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userCareerInfoService.getById(id));
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<UserCareerInfo>> getUserCareerInfoByUserId(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(userCareerInfoService.getByUserId(id));
	}
}
