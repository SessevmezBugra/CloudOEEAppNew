package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.OrderInfo;
import com.oee.repository.OrderInfoRepository;
import com.oee.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	
	private final OrderInfoRepository orderInfoRepository;
	
	public OrderInfoServiceImpl(OrderInfoRepository orderInfoRepository) {
		this.orderInfoRepository = orderInfoRepository;
	}

	@Override
	public OrderInfo create(OrderInfo orderInfo) {
		return orderInfoRepository.save(orderInfo);
	}

	@Override
	public OrderInfo update(OrderInfo orderInfo) {
		return orderInfoRepository.save(orderInfo);
	}

	@Override
	public Boolean delete(Long id) {
		orderInfoRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public OrderInfo getById(Long orderId) {
		return orderInfoRepository.findById(orderId).get();
	}

	@Override
	public List<OrderInfo> getByPlantId(Integer plantId) {
		return orderInfoRepository.findByPlantId(plantId);
	}

}
