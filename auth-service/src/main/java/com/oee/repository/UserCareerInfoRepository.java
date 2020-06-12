package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.UserCareerInfo;

@Repository
public interface UserCareerInfoRepository extends JpaRepository<UserCareerInfo, Long>{
	
	List<UserCareerInfo> findByUserUserId(Long userId);
	
}
