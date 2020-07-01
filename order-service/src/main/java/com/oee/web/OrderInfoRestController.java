package com.oee.web;

import java.util.List;

import com.oee.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.OrderInfo;
import com.oee.service.OrderInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.OrderInfoCtrl.CTRL)
public class OrderInfoRestController {
	
	private final OrderInfoService orderInfoService;
	
	public OrderInfoRestController(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<OrderInfo> createOrderInfo(@RequestBody OrderInfo orderInfo){
		return ResponseEntity.ok(orderInfoService.create(orderInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<OrderInfo> updateOrderInfo(@RequestBody OrderInfo orderInfo){
		return ResponseEntity.ok(orderInfoService.update(orderInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteOrderInfo(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(orderInfoService.delete(orderId));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<OrderDto>> getByLoggedUser(){
		return ResponseEntity.ok(orderInfoService.getByLoggedUser());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OrderInfo> getById(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(orderInfoService.getById(orderId));
	}
	
	@RequestMapping(value="/plant/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<OrderInfo>> getByPlantId(@PathVariable(value="id", required=true) Integer plantId){
		return ResponseEntity.ok(orderInfoService.getByPlantId(plantId));
	}
}
