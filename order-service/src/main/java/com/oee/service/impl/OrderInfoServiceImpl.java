package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.dto.OrderDto;
import com.oee.dto.PlantDto;
import com.oee.entity.OrderedMaterial;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.OrderInfo;
import com.oee.repository.OrderInfoRepository;
import com.oee.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	
	private final OrderInfoRepository orderInfoRepository;
	private final MainDataServiceClient mainDataServiceClient;
	private final ModelMapper modelMapper;
	
	public OrderInfoServiceImpl(OrderInfoRepository orderInfoRepository, MainDataServiceClient mainDataServiceClient, ModelMapper modelMapper) {
		this.orderInfoRepository = orderInfoRepository;
		this.mainDataServiceClient = mainDataServiceClient;
		this.modelMapper = modelMapper;
	}

	@Override
	public OrderInfo create(OrderInfo orderInfo) {
		OrderedMaterial orderedMaterial = orderInfo.getOrderedMaterial();
		orderedMaterial.setOrder(orderInfo);
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

	@Override
	public List<OrderDto> getByLoggedUser() {
		List<PlantDto> plantDtos = mainDataServiceClient.getPlantByLoggedUser().getBody();
		List<Long> ids = plantDtos.stream().map(PlantDto::getPlantId).collect(Collectors.toList());
		List<OrderInfo> orders = orderInfoRepository.findByPlantIdIn(ids);
		List<OrderDto> orderDtos = Arrays.asList(modelMapper.map(orders, OrderDto[].class));
		for (int i = 0; i < plantDtos.size(); i++) {
			PlantDto plantDto = plantDtos.get(i);
			for (int j = 0; j < orderDtos.size(); j++) {
				OrderDto orderDto = orderDtos.get(j);
				if (plantDto.getPlantId() == orderDto.getPlantId()){
					orderDto.setPlantName(plantDto.getPlantName());
				}
			}
		}
		return orderDtos;
	}

}
