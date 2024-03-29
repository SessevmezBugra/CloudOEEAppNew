package com.oee.web;

import java.util.List;

import com.oee.dto.OrderDto;
import org.springframework.core.annotation.Order;
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

	@RequestMapping(value="/plant/plant-ids", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteOrderByPlantIds(@RequestBody List<Long> plantIds){
		return ResponseEntity.ok(orderInfoService.deleteOrderByPlantIds(plantIds));
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
	public ResponseEntity<List<OrderInfo>> getByPlantId(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(orderInfoService.getByPlantId(plantId));
	}

	@RequestMapping(value="/start-order", method=RequestMethod.PUT)
	public ResponseEntity<OrderInfo> startOrderById(@RequestBody OrderInfo orderInfo){
		return ResponseEntity.ok(orderInfoService.startOrderById(orderInfo));
	}

	@RequestMapping(value="/hold-order", method=RequestMethod.PUT)
	public ResponseEntity<OrderInfo> holdOrderById(@RequestBody OrderInfo orderInfo){
		return ResponseEntity.ok(orderInfoService.holdOrder(orderInfo));
	}

	@RequestMapping(value="/complete-order", method=RequestMethod.PUT)
	public ResponseEntity<OrderInfo> completeOrderById(@RequestBody OrderInfo orderInfo){
		return ResponseEntity.ok(orderInfoService.completeOrder(orderInfo));
	}

	@RequestMapping(value="/resume-order", method=RequestMethod.PUT)
	public ResponseEntity<OrderInfo> resumeOrderById(@RequestBody OrderInfo orderInfo){
		return ResponseEntity.ok(orderInfoService.resumeOrder(orderInfo));
	}

	@RequestMapping(value="/active-order", method=RequestMethod.GET)
	public ResponseEntity<List<OrderDto>> getActiveOrdersByLoggedUser(){
		return ResponseEntity.ok(orderInfoService.getActiveOrdersByLoggedUser());
	}
}
