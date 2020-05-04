package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.Authority;
import com.oee.service.AuthorityService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.AuthorityCtrl.CTRL)
public class AuthorityRestController {

	private final AuthorityService authorityService;
	
	public AuthorityRestController(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Authority> createAuthority(@RequestBody Authority authority){
		return ResponseEntity.ok(authorityService.create(authority));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Authority> updateAuthority(@RequestBody Authority authority){
		return ResponseEntity.ok(authorityService.update(authority));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAuthority(@PathVariable(value="id", required=true) Long roleId){
		return ResponseEntity.ok(authorityService.delete(roleId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Authority> getAuthorityById(@PathVariable(value="id", required=true) Long roleId){
		return ResponseEntity.ok(authorityService.getById(roleId));
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Authority>> getAuthorityByUserId(@PathVariable(value="id", required=true) Long userId){
		return ResponseEntity.ok(authorityService.getByUserId(userId));
	}
	
}
