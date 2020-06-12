package com.oee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oee.dto.CompanyInfoDto;

//@RibbonClient(name = "maindata-service")
@FeignClient(name = "maindata-service")
public interface MainDataServiceProxy {

	@RequestMapping(value = "/rest/maindata/companyinfo", method=RequestMethod.POST)
	public ResponseEntity<CompanyInfoDto> createCompanyInfo(@RequestBody CompanyInfoDto companyInfoDto);
}