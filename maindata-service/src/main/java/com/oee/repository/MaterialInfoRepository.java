package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.MaterialInfo;

public interface MaterialInfoRepository extends JpaRepository<MaterialInfo, Long>{
	
	List<MaterialInfo> findByPlantPlantId(Integer plantId);
}
