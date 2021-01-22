package com.oee.repository;

import java.util.List;

import com.oee.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.OrderInfo;

import javax.transaction.Transactional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
	
	List<OrderInfo> findByPlantId(Long plantId);

    List<OrderInfo> findByPlantIdIn(Iterable<Long> ids);

    @Transactional
    void deleteByPlantIdIn(List<Long> plantIds);

    List<OrderInfo> findByPlantIdInAndOrderStatus(List<Long> ids, OrderStatus orderStatus);
}