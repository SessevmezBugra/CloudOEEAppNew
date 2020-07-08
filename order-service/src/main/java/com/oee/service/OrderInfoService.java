package com.oee.service;

import java.util.List;

import com.oee.dto.OrderDto;
import com.oee.entity.OrderInfo;

public interface OrderInfoService {
	
	OrderInfo create(OrderInfo orderInfo);
	
	OrderInfo update(OrderInfo orderInfo);
	
	Boolean delete(Long id);
	
	OrderInfo getById(Long orderId);
	
	List<OrderInfo> getByPlantId(Long plantId);

    List<OrderDto> getByLoggedUser();

    OrderInfo startOrderById(OrderInfo orderInfo);

	OrderInfo holdOrder(OrderInfo orderInfo);

	OrderInfo resumeOrder(OrderInfo orderInfo);

	OrderInfo completeOrder(OrderInfo orderInfo);
}
