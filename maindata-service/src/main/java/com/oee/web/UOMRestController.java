package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.UnitOfMeasureEntity;
import com.oee.service.UOMService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.UOMCtrl.CTRL)
public class UOMRestController {
	
	private final UOMService uomService;
	
	public UOMRestController(UOMService uomService) {
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
	public ResponseEntity<Boolean> deleteUOM(@PathVariable(value="id", required=true) Integer uomId){
		return ResponseEntity.ok(uomService.delete(uomId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UnitOfMeasureEntity> getUOMById(@PathVariable(value="id", required=true) Integer uomId){
		return ResponseEntity.ok(uomService.getById(uomId));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UnitOfMeasureEntity>> getUOMById(){
		return ResponseEntity.ok(uomService.getAll());
	}
}
