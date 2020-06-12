package com.oee.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.dto.CompanyInfoDto;
import com.oee.dto.RegisterDto;
import com.oee.entity.ResponsibleArea;
import com.oee.entity.User;
import com.oee.service.MainDataServiceProxy;
import com.oee.service.ResponsibleAreaService;
import com.oee.service.UserService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
public class UserRestController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ResponsibleAreaService responsibleAreaService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final UserService userService;
	private final MainDataServiceProxy mainDataServiceProxy;
	
	public UserRestController(UserService userService, MainDataServiceProxy mainDataServiceProxy) {
		this.userService = userService;
		this.mainDataServiceProxy = mainDataServiceProxy;
	}
	
	@RequestMapping(value="/registerowner", method = RequestMethod.POST)
	public ResponseEntity<Boolean> registerOwner(@RequestBody RegisterDto registerDto){
		logger.info("{}", environment.getProperty("local.server.port"));
		User user = new User();
		user.setPassword(registerDto.getUsername());
		user.setPassword(registerDto.getPassword());
		userService.registerOwner(user);
		CompanyInfoDto companyInfoDto = new CompanyInfoDto();
		companyInfoDto.setCompanyName(registerDto.getCompanyName());
		companyInfoDto = mainDataServiceProxy.createCompanyInfo(companyInfoDto).getBody();
		ResponsibleArea responsibleArea = new ResponsibleArea();
		responsibleArea.setAreaType("COMPANY");
		responsibleArea.setAreaId(companyInfoDto.getCompanyId());
		responsibleArea.setUser(user);
		responsibleAreaService.create(responsibleArea);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@RequestMapping(value="/registerworker", method = RequestMethod.POST)
	public ResponseEntity<Boolean> registerWorker(@RequestBody User user){
		userService.registerWorker(user);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@RequestMapping(value="/registerworker", method = RequestMethod.GET)
	public ResponseEntity<Boolean> registerWorker(){
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	
	
}
