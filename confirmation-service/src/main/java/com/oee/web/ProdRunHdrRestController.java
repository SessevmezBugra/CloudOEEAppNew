package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.ProdRunHdr;
import com.oee.service.ProdRunHdrService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ProdRunHdrCtrl.CTRL)
public class ProdRunHdrRestController {
	
	private final ProdRunHdrService prodRunHdrService;
	
	public ProdRunHdrRestController(ProdRunHdrService prodRunHdrService) {
		this.prodRunHdrService = prodRunHdrService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ProdRunHdr> createProdRunHdr(@RequestBody ProdRunHdr prodRunHdr){
		return ResponseEntity.ok(prodRunHdrService.create(prodRunHdr));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ProdRunHdr> updateProdRunHdr(@RequestBody ProdRunHdr prodRunHdr){
		return ResponseEntity.ok(prodRunHdrService.update(prodRunHdr));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProdRunHdr(@PathVariable(value="id", required=true) Long runId){
		return ResponseEntity.ok(prodRunHdrService.delete(runId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ProdRunHdr> getProdRunHdrById(@PathVariable(value="id", required=true) Long runId){
		return ResponseEntity.ok(prodRunHdrService.getById(runId));
	}
	
	@RequestMapping(value="/order/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ProdRunHdr>> getProdRunHdrByOrderId(@PathVariable(value="id", required=true) Long orderId){
		return ResponseEntity.ok(prodRunHdrService.getByOrderId(orderId));
	}

	@RequestMapping(value="/start", method=RequestMethod.POST)
	public ResponseEntity<ProdRunHdr> start(@RequestBody ProdRunHdr prodRunHdr){
		return ResponseEntity.ok(prodRunHdrService.start(prodRunHdr));
	}

	@RequestMapping(value="/hold", method=RequestMethod.PUT)
	public ResponseEntity<ProdRunHdr> hold(@RequestBody ProdRunHdr prodRunHdr){
		return ResponseEntity.ok(prodRunHdrService.hold(prodRunHdr));
	}

	@RequestMapping(value="/complete", method=RequestMethod.PUT)
	public ResponseEntity<ProdRunHdr> complete(@RequestBody ProdRunHdr prodRunHdr){
		return ResponseEntity.ok(prodRunHdrService.complete(prodRunHdr));
	}
}
