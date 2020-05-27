package com.oee.service;

import com.oee.entity.CompanyInfo;

public interface CompanyInfoService {
	
	CompanyInfo create(CompanyInfo companyInfo);
	
	CompanyInfo update(CompanyInfo companyInfo);
	
	Boolean delete(Long companyId);
	
	CompanyInfo getById(Long companyId);
	
}
