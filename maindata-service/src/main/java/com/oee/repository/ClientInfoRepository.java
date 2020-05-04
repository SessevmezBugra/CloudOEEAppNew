package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.ClientInfo;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Integer>{
	
	List<ClientInfo> findByCompanyCompanyId(Integer companyId);
	
}
