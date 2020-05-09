package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.CompanyInfo;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer>{
	
}
