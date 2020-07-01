package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.OrderInfo;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
	
	List<OrderInfo> findByPlantId(Integer plantId);

    List<OrderInfo> findByPlantIdIn(Iterable<Long> ids);
}