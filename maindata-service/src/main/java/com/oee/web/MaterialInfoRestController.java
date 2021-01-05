package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.MaterialInfo;
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
	public ResponseEntity<MaterialInfo> createMaterialInfo(@RequestBody MaterialInfo materialInfo){
		return ResponseEntity.ok(materialInfoService.create(materialInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<MaterialInfo> updateMaterialInfo(@RequestBody MaterialInfo materialInfo){
		return ResponseEntity.ok(materialInfoService.update(materialInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMaterialInfo(@PathVariable(value="id", required=true) Long materialId){
		return ResponseEntity.ok(materialInfoService.delete(materialId));
	}


	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MaterialInfo>> getMaterialByLoggedUser(){
		return ResponseEntity.ok(materialInfoService.getMaterialByLoggedUser());
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<MaterialInfo>> getMaterialByIds(@RequestBody List<Long> ids){
		return ResponseEntity.ok(materialInfoService.getMaterialsByIds(ids));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MaterialInfo> getMaterialInfoById(@PathVariable(value="id", required=true) Long materialId){
		return ResponseEntity.ok(materialInfoService.getById(materialId));
	}
	
	@RequestMapping(value="/plant/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<MaterialInfo>> getMaterialInfoByPlantId(@PathVariable(value="id", required=true) Long plantId){
		return ResponseEntity.ok(materialInfoService.getByPlantId(plantId));
	}
}
