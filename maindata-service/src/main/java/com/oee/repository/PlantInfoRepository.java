package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.PlantInfo;

public interface PlantInfoRepository extends JpaRepository<PlantInfo, Integer>{
	List<PlantInfo> findByClientClientId(Integer clientId);
}
