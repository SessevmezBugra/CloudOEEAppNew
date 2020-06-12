package com.oee.service;

import com.oee.entity.User;

public interface UserService {
	
	User findByUsername(String username);
	
	User registerOwner(User user);
	
	User registerWorker(User user);

}
