package com.oee.web;

import java.util.List;

import com.oee.entity.WarehouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.WarehouseService;
import com.oee.util.constant.ApiPaths;

@RestController
@RequestMapping(ApiPaths.WarehouseCtrl.CTRL)
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<WarehouseEntity> createWarehouseInfo(@RequestBody WarehouseEntity warehouseEntity){
		return ResponseEntity.ok(warehouseService.create(warehouseEntity));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<WarehouseEntity> updateWarehouseInfo(@RequestBody WarehouseEntity warehouseEntity){
		return ResponseEntity.ok(warehouseService.update(warehouseEntity));
	}
	
	@RequestMapping(value="/{warehouseId}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteWarehouseInfoById(@PathVariable(value="warehouseId", required=true) Long warehouseId){
		warehouseService.deleteById(warehouseId);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{warehouseId}", method=RequestMethod.GET)
	public ResponseEntity<WarehouseEntity> getWarehouseInfoById(@PathVariable(value="warehouseId", required=true) Long warehouseId){
		return ResponseEntity.ok(warehouseService.findById(warehouseId));
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<WarehouseEntity>> getWarehousesInfoByIds(@RequestBody List<Long> warehouseIds){
		return ResponseEntity.ok(warehouseService.findByIds(warehouseIds));
	}

}
