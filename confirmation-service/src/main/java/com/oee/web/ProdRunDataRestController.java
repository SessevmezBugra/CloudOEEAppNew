package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.ProdRunData;
import com.oee.service.ProdRunDataService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ProdRunDataCtrl.CTRL)
public class ProdRunDataRestController {

	private final ProdRunDataService prodRunDataService;
	
	public ProdRunDataRestController(ProdRunDataService prodRunDataService) {
		this.prodRunDataService = prodRunDataService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ProdRunData> createProdRunData(@RequestBody ProdRunData prodRunData){
		return ResponseEntity.ok(prodRunDataService.create(prodRunData));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ProdRunData> updateProdRunData(@RequestBody ProdRunData prodRunData){
		return ResponseEntity.ok(prodRunDataService.update(prodRunData));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProdRunDataById(@PathVariable(value="id", required=true) Long entryId){
		return ResponseEntity.ok(prodRunDataService.delete(entryId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ProdRunData> getProdRunDataById(@PathVariable(value="id", required=true) Long entryId){
		return ResponseEntity.ok(prodRunDataService.getById(entryId));
	}
	
	@RequestMapping(value="/prodrunhdr/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ProdRunData>> getProdRunDataByRunId(@PathVariable(value="id", required=true) Long runId){
		return ResponseEntity.ok(prodRunDataService.getByRunId(runId));
	}
	
}
