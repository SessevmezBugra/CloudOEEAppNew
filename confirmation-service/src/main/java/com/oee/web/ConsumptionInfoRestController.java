package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.ConsumptionInfo;
import com.oee.service.ConsumptionInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ConsumptionInfoCtrl.CTRL)
public class ConsumptionInfoRestController {
	
	private final ConsumptionInfoService consumptionInfoService;
	
	public ConsumptionInfoRestController(ConsumptionInfoService consumptionInfoService) {
		this.consumptionInfoService = consumptionInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ConsumptionInfo> createConsumptionInfo(@RequestBody ConsumptionInfo consumptionInfo){
		return ResponseEntity.ok(consumptionInfoService.create(consumptionInfo));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<List<ConsumptionInfo>> createAllConsumptionInfo(@PathVariable(value="id", required=true) Long orderId, @RequestBody List<ConsumptionInfo> consumptionInfos){
		return ResponseEntity.ok(consumptionInfoService.createAll(orderId, consumptionInfos));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ConsumptionInfo> updateConsumptionInfo(@RequestBody ConsumptionInfo consumptionInfo){
		return ResponseEntity.ok(consumptionInfoService.update(consumptionInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteConsumptionInfo(@PathVariable(value="id", required=true) Long consumptionId){
		return ResponseEntity.ok(consumptionInfoService.delete(consumptionId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ConsumptionInfo> getConsumptionInfoById(@PathVariable(value="id", required=true) Long consumptionId){
		return ResponseEntity.ok(consumptionInfoService.getById(consumptionId));
	}
	
	@RequestMapping(value="/run-id/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ConsumptionInfo>> getConsumptionInfoByOrderId(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(consumptionInfoService.getByRunId(orderId));
	}
	
}
