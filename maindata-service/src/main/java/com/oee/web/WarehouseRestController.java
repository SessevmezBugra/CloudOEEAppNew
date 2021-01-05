package com.oee.web;

import java.util.List;

import com.oee.dto.WarehouseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.WarehouseInfo;
import com.oee.service.WarehouseService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.WarehouseInfoCtrl.CTRL)
public class WarehouseRestController {

	@Autowired
	private WarehouseService warehouseService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<WarehouseInfo> createWarehouseInfo(@RequestBody WarehouseInfo warehouseInfo){
		return ResponseEntity.ok(warehouseService.create(warehouseInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<WarehouseInfo> updateWarehouseInfo(@RequestBody WarehouseInfo warehouseInfo){
		return ResponseEntity.ok(warehouseService.update(warehouseInfo));
	}
	
	@RequestMapping(value="/{warehouseId}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteWarehouseInfoById(@PathVariable(value="warehouseId", required=true) Long warehouseId){
		return ResponseEntity.ok(warehouseService.deleteById(warehouseId));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<WarehouseDto>> getWarehouseByLoggedUser(){
		return ResponseEntity.ok(warehouseService.getWarehouseByLoggedUser());
	}
	
	@RequestMapping(value="/{warehouseId}", method=RequestMethod.GET)
	public ResponseEntity<WarehouseInfo> getWarehouseInfoById(@PathVariable(value="warehouseId", required=true) Long warehouseId){
		return ResponseEntity.ok(warehouseService.getById(warehouseId));
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<WarehouseInfo>> getWarehousesInfoByIds(@RequestBody List<Long> warehouseIds){
		return ResponseEntity.ok(warehouseService.getByIds(warehouseIds));
	}
	
	@RequestMapping(value="/plant/{plantId}", method=RequestMethod.GET)
	public ResponseEntity<List<WarehouseInfo>> getWarehouseInfoByPlantId(@PathVariable(value="plantId", required=true) Long plantId){
		return ResponseEntity.ok(warehouseService.findByPlantId(plantId));
	}
}
