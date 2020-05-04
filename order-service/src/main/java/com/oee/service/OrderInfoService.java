package com.oee.service;

import java.util.List;

import com.oee.entity.OrderInfo;

public interface OrderInfoService {
	
	OrderInfo create(OrderInfo orderInfo);
	
	OrderInfo update(OrderInfo orderInfo);
	
	Boolean delete(Long id);
	
	OrderInfo getById(Long orderId);
	
	List<OrderInfo> getByPlantId(Integer plantId);
}
