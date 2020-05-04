package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.ConsumptionMaterial;

public interface ConsumptionMaterialRepository extends JpaRepository<ConsumptionMaterial, Long>{
	
	List<ConsumptionMaterial> findByOrderOrderId(Long orderId);
	
}
