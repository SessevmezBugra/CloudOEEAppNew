package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.ConfirmationServiceClient;
import com.oee.client.MainDataServiceClient;
import com.oee.config.CurrentUserProvider;
import com.oee.dto.MaterialDto;
import com.oee.dto.OrderDto;
import com.oee.dto.PlantDto;
import com.oee.dto.ProdRunHdrDto;
import com.oee.entity.OrderedMaterial;
import com.oee.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oee.entity.OrderInfo;
import com.oee.repository.OrderInfoRepository;
import com.oee.service.OrderInfoService;

@Service
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService{
	
	private final OrderInfoRepository orderInfoRepository;
	private final MainDataServiceClient mainDataServiceClient;
	private final ModelMapper modelMapper;
	private final CurrentUserProvider currentUserProvider;
	private final ConfirmationServiceClient confirmationServiceClient;

	@Override
	public OrderInfo create(OrderInfo orderInfo) {
		OrderedMaterial orderedMaterial = orderInfo.getOrderedMaterial();
		if (orderedMaterial == null) {
			throw new RuntimeException("Lutfen uretim malzemesini doldurunuz.");
		}
		if (orderedMaterial.getIsStockProd()) {
			MaterialDto materialDto = mainDataServiceClient.getMaterialById(orderedMaterial.getMaterialId()).getBody();
			orderedMaterial.setMaterialDesc(materialDto.getMaterialDesc());
			orderedMaterial.setMaterialNumber(materialDto.getMaterialNumber());
		}
		orderedMaterial.setOrder(orderInfo);
		orderInfo.setOrderStatus(OrderStatus.NEW);
		orderInfo.setCreatedUser(currentUserProvider.getCurrentUser().getUsername());
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
	public Boolean deleteOrderByPlantIds(List<Long> plantIds) {
		List<OrderInfo> orders = orderInfoRepository.findByPlantIdIn(plantIds);
		List<Long> orderIds = orders.stream().map(OrderInfo::getOrderId).collect(Collectors.toList());
		orderInfoRepository.deleteByPlantIdIn(plantIds);
		confirmationServiceClient.deleteByOrderIds(orderIds);
		return Boolean.TRUE;
	}

	@Override
	public OrderInfo getById(Long orderId) {
		return orderInfoRepository.findById(orderId).get();
	}

	@Override
	public List<OrderInfo> getByPlantId(Long plantId) {
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

	@Override
	public OrderInfo startOrderById(OrderInfo orderInfoDto) {
		OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDto.getOrderId()).orElseThrow(() -> new RuntimeException("Boyle bir siparis bulunmamaktadir."));
		if (orderInfo.getOrderStatus() != OrderStatus.NEW && orderInfo.getOrderStatus() != OrderStatus.HOLD) {
			throw new RuntimeException("Siparis durumu baslatmak icin uygun degildir.");
		}
		orderInfo.setOrderStatus(OrderStatus.ACT);
		if (orderInfo.getActualStartDate() == null) {
			orderInfo.setActualStartDate(orderInfoDto.getActualStartDate());
		}
		orderInfoRepository.save(orderInfo);
		ProdRunHdrDto prodRunHdrDto = new ProdRunHdrDto();
		prodRunHdrDto.setOrderId(orderInfo.getOrderId());
		//orderInfoDto.getActualStartDate() -->
		// bunun sebebi siparisin bir baslangic tarihi olur ama prodrunhdr icin her startin baslangic tarihi tutulur.
		prodRunHdrDto.setStartTime(orderInfoDto.getActualStartDate());
		confirmationServiceClient.start(prodRunHdrDto);
		return orderInfo;
	}

	@Override
	public OrderInfo holdOrder(OrderInfo orderInfoDto) {
		OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDto.getOrderId()).orElseThrow(() -> new RuntimeException("Boyle bir siparis bulunmamaktadir."));
		if (orderInfo.getOrderStatus() != OrderStatus.ACT) {
			throw new RuntimeException("Siparis durumu bekletmek icin uygun degildir.");
		}
		orderInfo.setOrderStatus(OrderStatus.HOLD);
		orderInfoRepository.save(orderInfo);
		ProdRunHdrDto prodRunHdrDto = new ProdRunHdrDto();
		prodRunHdrDto.setOrderId(orderInfo.getOrderId());
		prodRunHdrDto.setEndTime(orderInfoDto.getActualEndDate());
		confirmationServiceClient.hold(prodRunHdrDto);
		return orderInfo;
	}

	@Override
	public OrderInfo resumeOrder(OrderInfo orderInfoDto) {
		OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDto.getOrderId()).orElseThrow(() -> new RuntimeException("Boyle bir siparis bulunmamaktadir."));
		if (orderInfo.getOrderStatus() != OrderStatus.HOLD) {
			throw new RuntimeException("Siparis durumu devam etmek icin uygun degildir.");
		}
		orderInfo.setOrderStatus(OrderStatus.ACT);
		orderInfoRepository.save(orderInfo);
		ProdRunHdrDto prodRunHdrDto = new ProdRunHdrDto();
		prodRunHdrDto.setOrderId(orderInfo.getOrderId());
		prodRunHdrDto.setStartTime(orderInfoDto.getActualStartDate());
		confirmationServiceClient.start(prodRunHdrDto);
		return orderInfo;
	}

	@Override
	public OrderInfo completeOrder(OrderInfo orderInfoDto) {
		OrderInfo orderInfo = orderInfoRepository.findById(orderInfoDto.getOrderId()).orElseThrow(() -> new RuntimeException("Boyle bir siparis bulunmamaktadir."));
		if (orderInfo.getOrderStatus() != OrderStatus.ACT) {
			throw new RuntimeException("Siparisi tamamlamak icin baslatmaniz gereklidir.");
		}
		orderInfo.setOrderStatus(OrderStatus.CMPL);
		orderInfoRepository.save(orderInfo);
		ProdRunHdrDto prodRunHdrDto = new ProdRunHdrDto();
		prodRunHdrDto.setOrderId(orderInfo.getOrderId());
		prodRunHdrDto.setEndTime(orderInfoDto.getActualEndDate());
		confirmationServiceClient.complete(prodRunHdrDto);
		return orderInfo;
	}

	@Override
	public List<OrderDto> getActiveOrdersByLoggedUser() {
		List<PlantDto> plantDtos = mainDataServiceClient.getPlantByLoggedUser().getBody();
		List<Long> ids = plantDtos.stream().map(PlantDto::getPlantId).collect(Collectors.toList());
		List<OrderInfo> orders = orderInfoRepository.findByPlantIdInAndOrderStatus(ids, OrderStatus.ACT);
		List<OrderDto> orderDtos = Arrays.asList(modelMapper.map(orders, OrderDto[].class));
		return orderDtos;
	}

}
