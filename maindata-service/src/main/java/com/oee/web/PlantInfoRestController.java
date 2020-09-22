package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.PlantInfo;
import com.oee.service.PlantInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.PlantInfoCtrl.CTRL)
public class PlantInfoRestController {
	
	private final PlantInfoService plantInfoService;
	
	public PlantInfoRestController(PlantInfoService plantInfoService) {
		this.plantInfoService = plantInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PlantInfo> createPlantInfo(@RequestBody PlantInfo plantInfo){
		return ResponseEntity.ok(plantInfoService.create(plantInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<PlantInfo> updatePlantInfo(@RequestBody PlantInfo plantInfo){
		return ResponseEntity.ok(plantInfoService.update(plantInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePlantInfo(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(plantInfoService.delete(plantId));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PlantInfo>> getPlantByLoggedUser(){
		return ResponseEntity.ok(plantInfoService.getPlantByLoggedUser());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PlantInfo> getPlantInfoById(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(plantInfoService.getById(plantId));
	}
	
	@RequestMapping(value="/client/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<PlantInfo>> getPlantInfoByClientId(@PathVariable(value="id", required=true) Long clientId){
		return ResponseEntity.ok(plantInfoService.getByClientId(clientId));
	}

	@RequestMapping(value="/warehouse/{id}", method=RequestMethod.GET)
	public ResponseEntity<PlantInfo> getPlantInfoByWarehouseId(@PathVariable(value="id", required=true) Long warehouseId){
		return ResponseEntity.ok(plantInfoService.getByWarehouseId(warehouseId));
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<PlantInfo>> getPlantsByIds(@RequestBody List<Long> ids) {
		return ResponseEntity.ok(plantInfoService.getPlantByIds(ids));
	}
}
