package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.ConsumptionInfo;

public interface ConsumptionInfoRepository extends JpaRepository<ConsumptionInfo, Long>{
	
	List<ConsumptionInfo> findByOrderId(Long orderId);

}
