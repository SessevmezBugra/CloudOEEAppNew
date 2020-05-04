package com.oee.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.CompanyInfo;
import com.oee.service.CompanyInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.CompanyInfoCtrl.CTRL)
public class CompanyInfoRestController {
	
	
	@Autowired
	private Environment environment;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final CompanyInfoService companyInfoService;
	
	public CompanyInfoRestController(CompanyInfoService companyInfoService) {
		this.companyInfoService = companyInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CompanyInfo> createCompanyInfo(@RequestBody CompanyInfo companyInfo){
		logger.info("{}", environment.getProperty("local.server.port"));
		return ResponseEntity.ok(companyInfoService.create(companyInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<CompanyInfo> updateCompanyInfo(@RequestBody CompanyInfo companyInfo){
		return ResponseEntity.ok(companyInfoService.update(companyInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteCompanyInfo(@PathVariable(value="id", required=true) Integer companyId){
		return ResponseEntity.ok(companyInfoService.delete(companyId));
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CompanyInfo> getCompanyInfoById(@PathVariable(value="id", required=true) Integer companyId){
		return ResponseEntity.ok(companyInfoService.getById(companyId));
	}
	
	@RequestMapping(value="/user/{username}", method=RequestMethod.GET)
	public ResponseEntity<CompanyInfo> getCompanyInfoByUsername(@PathVariable(value="username", required=true) String username){
		return ResponseEntity.ok(companyInfoService.getByUsername(username));
	}
}
