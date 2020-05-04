package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.StockMovement;
import com.oee.service.StockMovementService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.StockMovementCtrl.CTRL)
public class StockMovementRestController {
	
	private final StockMovementService stockMovementService;
	
	public StockMovementRestController(StockMovementService stockMovementService) {
		this.stockMovementService = stockMovementService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<StockMovement> createStockMovement(@RequestBody StockMovement stockMovement){
		return ResponseEntity.ok(stockMovementService.create(stockMovement));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<StockMovement> updateStockMovement(@RequestBody StockMovement stockMovement){
		return ResponseEntity.ok(stockMovementService.update(stockMovement));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteStockMovement(@PathVariable(value="id", required=true) Long stockMovementId){
		return ResponseEntity.ok(stockMovementService.delete(stockMovementId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StockMovement> getStockMovementById(@PathVariable(value="id", required=true) Long stockMovementId){
		return ResponseEntity.ok(stockMovementService.getById(stockMovementId));
	}
	
	@RequestMapping(value="/stockinfo/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<StockMovement>> getStockMovementByStockInfoId(@PathVariable(value="id", required=true) Long stockMovementId){
		return ResponseEntity.ok(stockMovementService.getByStockId(stockMovementId));
	}
	
}
