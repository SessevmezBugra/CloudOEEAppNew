package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.MaterialInfo;

@Repository
public interface MaterialInfoRepository extends JpaRepository<MaterialInfo, Long>{
	
	List<MaterialInfo> findByPlantPlantId(Long plantId);
}
