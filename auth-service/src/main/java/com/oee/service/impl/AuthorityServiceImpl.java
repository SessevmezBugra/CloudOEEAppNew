package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.Authority;
import com.oee.repository.AuthorityRepository;
import com.oee.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	private final AuthorityRepository authorityRepository;
	
	public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}
	
	@Override
	public Authority create(Authority authority) {
		return authorityRepository.save(authority);
	}

	@Override
	public Authority update(Authority authority) {
		return authorityRepository.save(authority);
	}

	@Override
	public Boolean delete(Long roleId) {
		authorityRepository.deleteById(roleId);
		return Boolean.TRUE;
	}

	@Override
	public Authority getById(Long roleId) {
		return authorityRepository.findById(roleId).get();
	}

	@Override
	public List<Authority> getByUserId(Long userId) {
		return authorityRepository.findByUserUserId(userId);
	}

}
