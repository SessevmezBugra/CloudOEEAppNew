package com.oee.service;

import com.oee.entity.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {

	List<CompanyInfo> findCompanies();

	CompanyInfo create(CompanyInfo companyInfo);
	
	CompanyInfo update(CompanyInfo companyInfo);
	
	Boolean delete(Long companyId);
	
	CompanyInfo getById(Long companyId);
	
}
