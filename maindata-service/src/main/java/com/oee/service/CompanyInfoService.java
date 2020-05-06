package com.oee.service;

import com.oee.entity.CompanyInfo;

public interface CompanyInfoService {
	
	CompanyInfo create(CompanyInfo companyInfo);
	
	CompanyInfo update(CompanyInfo companyInfo);
	
	Boolean delete(Integer companyId);
	
	CompanyInfo getById(Integer companyId);
	
}
