package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.UnitOfMeasures;
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
	public ResponseEntity<UnitOfMeasures> createUOM(@RequestBody UnitOfMeasures unitOfMeasures){
		return ResponseEntity.ok(uomService.create(unitOfMeasures));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<UnitOfMeasures> updateUOM(@RequestBody UnitOfMeasures unitOfMeasures){
		return ResponseEntity.ok(uomService.update(unitOfMeasures));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUOM(@PathVariable(value="id", required=true) Integer uomId){
		return ResponseEntity.ok(uomService.delete(uomId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UnitOfMeasures> getUOMById(@PathVariable(value="id", required=true) Integer uomId){
		return ResponseEntity.ok(uomService.getById(uomId));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UnitOfMeasures>> getUOMById(){
		return ResponseEntity.ok(uomService.getAll());
	}
}
