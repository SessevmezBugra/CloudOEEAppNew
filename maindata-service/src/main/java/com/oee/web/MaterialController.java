package com.oee.web;

import java.util.List;

import com.oee.entity.MaterialEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.MaterialService;
import com.oee.util.constant.ApiPaths;

@RestController
@RequestMapping(ApiPaths.MaterialCtrl.CTRL)
@RequiredArgsConstructor
public class MaterialController {
	
	private final MaterialService materialService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<MaterialEntity> createMaterialInfo(@RequestBody MaterialEntity materialEntity){
		return ResponseEntity.ok(materialService.create(materialEntity));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<MaterialEntity> updateMaterialInfo(@RequestBody MaterialEntity materialEntity){
		return ResponseEntity.ok(materialService.update(materialEntity));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMaterialInfo(@PathVariable(value="id", required=true) Long materialId){
		materialService.deleteById(materialId);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<MaterialEntity>> getMaterialByIds(@RequestBody List<Long> ids){
		return ResponseEntity.ok(materialService.findMaterialsByIds(ids));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MaterialEntity> getMaterialInfoById(@PathVariable(value="id", required=true) Long materialId){
		return ResponseEntity.ok(materialService.findById(materialId));
	}

}
