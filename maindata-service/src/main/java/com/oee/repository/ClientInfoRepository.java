package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.ClientInfo;

@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long>{
	
	List<ClientInfo> findByCompanyCompanyId(Long companyId);

	List<ClientInfo> findByCompanyCompanyIdIn(Iterable<Long> ids);
}
