package com.oee.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.OrderedMaterial;
import com.oee.service.OrderedMaterialService;
import com.oee.util.ApiPaths;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.OrderedMaterialCtrl.CTRL)
public class OrderedMaterialRestController {

	private final OrderedMaterialService orderedMaterialService;
	
	public OrderedMaterialRestController(OrderedMaterialService orderedMaterialService) {
		this.orderedMaterialService = orderedMaterialService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<OrderedMaterial> createOrderedMaterial(@RequestBody OrderedMaterial orderedMaterial){
		return ResponseEntity.ok(orderedMaterialService.create(orderedMaterial));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<OrderedMaterial> updateOrderedMaterial(@RequestBody OrderedMaterial orderedMaterial){
		return ResponseEntity.ok(orderedMaterialService.update(orderedMaterial));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteOrderedMaterial(@PathVariable(value="id", required=true) Long orderedMaterialId){
		return ResponseEntity.ok(orderedMaterialService.delete(orderedMaterialId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OrderedMaterial> getOrderedMaterialById(@PathVariable(value="id", required=true) Long orderedMaterialId){
		return ResponseEntity.ok(orderedMaterialService.getById(orderedMaterialId));
	}
	
	@RequestMapping(value="/order/{id}", method=RequestMethod.GET)
	public ResponseEntity<OrderedMaterial> getOrderedMaterialByOrderId(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(orderedMaterialService.getByOrderId(orderId));
	}
	
}
