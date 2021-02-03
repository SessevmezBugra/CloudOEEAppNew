package com.oee.web;

import java.util.List;

import com.oee.entity.MaterialEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.MaterialInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.MaterialInfoCtrl.CTRL)
public class MaterialInfoRestController {
	
	private final MaterialInfoService materialInfoService;
	
	public MaterialInfoRestController(MaterialInfoService materialInfoService) {
		this.materialInfoService = materialInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<MaterialEntity> createMaterialInfo(@RequestBody MaterialEntity materialEntity){
		return ResponseEntity.ok(materialInfoService.create(materialEntity));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<MaterialEntity> updateMaterialInfo(@RequestBody MaterialEntity materialEntity){
		return ResponseEntity.ok(materialInfoService.update(materialEntity));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMaterialInfo(@PathVariable(value="id", required=true) Long materialId){
		return ResponseEntity.ok(materialInfoService.delete(materialId));
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<MaterialEntity>> getMaterialByIds(@RequestBody List<Long> ids){
		return ResponseEntity.ok(materialInfoService.getMaterialsByIds(ids));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MaterialEntity> getMaterialInfoById(@PathVariable(value="id", required=true) Long materialId){
		return ResponseEntity.ok(materialInfoService.getById(materialId));
	}

}
