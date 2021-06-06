package com.oee.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.UnitOfMeasureEntity;
import com.oee.service.UOMService;
import com.oee.util.constant.ApiPaths;

@RestController
@RequestMapping(ApiPaths.UOMCtrl.CTRL)
public class UOMController {
	
	private final UOMService uomService;
	
	public UOMController(UOMService uomService) {
		this.uomService = uomService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UnitOfMeasureEntity> createUOM(@RequestBody UnitOfMeasureEntity unitOfMeasureEntity){
		return ResponseEntity.ok(uomService.create(unitOfMeasureEntity));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<UnitOfMeasureEntity> updateUOM(@RequestBody UnitOfMeasureEntity unitOfMeasureEntity){
		return ResponseEntity.ok(uomService.update(unitOfMeasureEntity));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity deleteUOM(@PathVariable(value="id", required=true) Long uomId){
		uomService.deleteById(uomId);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UnitOfMeasureEntity> getUOMById(@PathVariable(value="id", required=true) Long uomId){
		return ResponseEntity.ok(uomService.findById(uomId));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UnitOfMeasureEntity>> getUOMById(){
		return ResponseEntity.ok(uomService.findAll());
	}
}
