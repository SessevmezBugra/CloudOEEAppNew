package com.oee.web;

import com.oee.entity.PlantEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.PlantService;
import com.oee.util.constant.ApiPaths;

@RestController
@RequestMapping(ApiPaths.PlantCtrl.CTRL)
public class PlantController {
	
	private final PlantService plantService;
	
	public PlantController(PlantService plantService) {
		this.plantService = plantService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PlantEntity> createPlantInfo(@RequestBody PlantEntity plantEntity){
		return ResponseEntity.ok(plantService.create(plantEntity));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<PlantEntity> updatePlantInfo(@RequestBody PlantEntity plantEntity){
		return ResponseEntity.ok(plantService.update(plantEntity));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePlantInfo(@PathVariable(value="id", required=true) Long plantId){
		plantService.deleteById(plantId);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PlantEntity> getPlantInfoById(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(plantService.findById(plantId));
	}

}
