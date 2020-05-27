package com.oee.service.impl;

import org.springframework.stereotype.Service;

import com.oee.entity.CompanyInfo;
import com.oee.repository.CompanyInfoRepository;
import com.oee.service.CompanyInfoService;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService{

	private final CompanyInfoRepository companyInfoRepository;
	
	public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository) {
		this.companyInfoRepository = companyInfoRepository;
	}
	
	@Override
	public CompanyInfo create(CompanyInfo companyInfo) {
		return companyInfoRepository.save(companyInfo);
	}

	@Override
	public CompanyInfo update(CompanyInfo companyInfo) {
		return companyInfoRepository.save(companyInfo);
	}

	@Override
	public Boolean delete(Long companyId) {
		companyInfoRepository.deleteById(companyId);
		return Boolean.TRUE;
	}

	@Override
	public CompanyInfo getById(Long companyId) {
		return companyInfoRepository.findById(companyId).get();
	}

}
