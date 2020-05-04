package com.oee.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token/rest")
public class TokenRestController {


	@RequestMapping(value = "/validatetoken", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validateToken() {
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
