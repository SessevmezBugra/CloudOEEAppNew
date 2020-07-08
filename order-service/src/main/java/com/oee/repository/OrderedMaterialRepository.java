package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.OrderedMaterial;

import java.util.List;

public interface OrderedMaterialRepository extends JpaRepository<OrderedMaterial, Long>{
	
	OrderedMaterial findByOrderOrderId(Long orderId);
	
}
