package com.oee.web;

import java.util.List;

import com.oee.dto.ConsumptionStockDto;
import com.oee.entity.ConsumptionStock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.ConsumptionMaterialService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ConsumptionMaterialCtrl.CTRL)
public class ConsumptionMaterialRestController {
	
	private final ConsumptionMaterialService consumptionMaterialService;
	
	public ConsumptionMaterialRestController(ConsumptionMaterialService consumptionMaterialService) {
		this.consumptionMaterialService = consumptionMaterialService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ConsumptionStock> createConsumptionMaterial(@RequestBody ConsumptionStock consumptionStock){
		return ResponseEntity.ok(consumptionMaterialService.create(consumptionStock));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ConsumptionStock> updateConsumptionMaterial(@RequestBody ConsumptionStock consumptionStock){
		return ResponseEntity.ok(consumptionMaterialService.update(consumptionStock));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteConsumptionMaterial(@PathVariable(value="id", required=true) Long consumptionMaterialId){
		return ResponseEntity.ok(consumptionMaterialService.delete(consumptionMaterialId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ConsumptionStock> getConsumptionMaterialById(@PathVariable(value="id", required=true) Long consumptionMaterialId){
		return ResponseEntity.ok(consumptionMaterialService.getById(consumptionMaterialId));
	}
	
	@RequestMapping(value="/order/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ConsumptionStockDto>> getConsumptionMaterialByOrderId(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(consumptionMaterialService.getByOrderId(orderId));
	}

	@RequestMapping(value="/without-warehouse-info/order/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ConsumptionStockDto>> getByOrderIdWithoutWarehouseInfo(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(consumptionMaterialService.getByOrderIdWithoutWarehouseInfo(orderId));
	}
}
