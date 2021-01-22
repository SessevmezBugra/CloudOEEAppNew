package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.Plant;
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
	public ResponseEntity<Plant> createPlantInfo(@RequestBody Plant plant){
		return ResponseEntity.ok(plantInfoService.create(plant));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Plant> updatePlantInfo(@RequestBody Plant plant){
		return ResponseEntity.ok(plantInfoService.update(plant));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePlantInfo(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(plantInfoService.delete(plantId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Plant> getPlantInfoById(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(plantInfoService.getById(plantId));
	}

}
