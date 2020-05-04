package com.oee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oee.service.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.err.println(username);
		com.oee.entity.User user = userService.findByUsername(username);
		System.err.println("loadUserByUsername");
		
		if(user != null) {
			System.err.println("user");
			List<String> auths = user.getAuthorities().stream().map(auth -> "ROLE_" + auth.getRole()).collect(Collectors.toList());
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(auths.stream().toArray(String[]::new));
			System.err.println("OOOKKK");
			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
}