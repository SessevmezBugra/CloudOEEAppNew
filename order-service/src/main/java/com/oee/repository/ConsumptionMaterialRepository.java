package com.oee.repository;

import java.util.List;

import com.oee.entity.ConsumptionStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionMaterialRepository extends JpaRepository<ConsumptionStock, Long>{
	
	List<ConsumptionStock> findByOrderOrderId(Long orderId);
	
}
