package com.oee.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oee.entity.Authority;
import com.oee.entity.User;
import com.oee.repository.UserRepository;
import com.oee.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
//	@Override
//	public User register(User user) {
//		String password = passwordEncoder.encode(user.getPassword());
//		Authority authority = new Authority();
//		authority.setRole("CUSTOMER");
//		authority.setUser(user);
//		user.addAuthority(authority);
//		user.setPassword(password);
//		User registeredUser = userRepository.saveAndFlush(user);
//		return registeredUser;
//	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		
		return userRepository.findByUsername(username);
	}

	@Override
	public User registerOwner(User user) {
		System.err.println("USER NAME : " + user.getUsername());
		if(this.findByUsername(user.getUsername()) != null) throw new IllegalArgumentException("Bu kullanici mevcuttur.");
		Authority authority = new Authority();
		authority.setRole("COMPANY_OWNER");
		authority.setUser(user);
		user.addAuthority(authority);
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User registerWorker(User user) {
		if(this.findByUsername(user.getUsername()) != null) throw new IllegalArgumentException("Bu kullanici mevcuttur.");
		Authority authority = new Authority();
		authority.setRole("WORKER");
		authority.setUser(user);
		user.addAuthority(authority);
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		return userRepository.saveAndFlush(user);
	}
	
}
