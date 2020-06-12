package com.oee.service;

import java.util.List;

import com.oee.entity.Authority;

public interface AuthorityService {
	
	Authority create(Authority authority);
	
	Authority update(Authority authority);
	
	Boolean delete(Long roleId);
	
	Authority getById(Long roleId);
	
	List<Authority> getByUserId(Long userId);
	
}
