package com.oee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.oee.service.TokenService;
import com.oee.util.JwtTokenUtil;

@Service
public class TokenServiceImpl implements TokenService{


	@Autowired
	private JwtTokenUtil jwtUtil;
	
	
	@Override
	public Boolean validateToken(String token) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			name = userDetails.getUsername();
		}else {
		    name = auth.getName();
		}
		return jwtUtil.validateToken(token, name);
	}

}
