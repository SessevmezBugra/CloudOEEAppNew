package com.oee.web;

import java.util.List;

import com.oee.dto.StockDto;
import com.oee.entity.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.StockInfoService;
import com.oee.util.ApiPaths;


@RestController
@RequestMapping(ApiPaths.StockInfoCtrl.CTRL)
public class StockInfoRestController {
	
	private final StockInfoService stockInfoService;
	
	public StockInfoRestController(StockInfoService stockInfoService) {
		this.stockInfoService = stockInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Stock> createStockInfo(@RequestBody Stock stock){
		
//        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
//        AccessToken accessToken = session.getToken();
//        username = accessToken.getPreferredUsername();
//        emailID = accessToken.getEmail();
//        lastname = accessToken.getFamilyName();
//        firstname = accessToken.getGivenName();
//        realmName = accessToken.getIssuer();            
//        Access realmAccess = accessToken.getRealmAccess();
//        roles = realmAccess.getRoles();
//		System.err.println(accessToken.toString());
		return ResponseEntity.ok(stockInfoService.create(stock));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Stock> updateStockInfo(@RequestBody Stock stock){
		return ResponseEntity.ok(stockInfoService.update(stock));
	}
	
	@RequestMapping(value="/add",method=RequestMethod.PUT)
	public ResponseEntity<Stock> addStockInfo(@RequestBody Stock stock){
		return ResponseEntity.ok(stockInfoService.addStock(stock));
	}
	
	@RequestMapping(value="/extract",method=RequestMethod.PUT)
	public ResponseEntity<Stock> extractStockInfo(@RequestBody Stock stock){
		return ResponseEntity.ok(stockInfoService.extractStock(stock));
	}

	@RequestMapping(value="/extract/all",method=RequestMethod.PUT)
	public ResponseEntity<List<Stock>> extractAllStockInfo(@RequestBody List<Stock> stocks){
		return ResponseEntity.ok(stockInfoService.extractAllStock(stocks));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteStockInfo(@PathVariable(value="id", required=true) Long stockId){
		return ResponseEntity.ok(stockInfoService.delete(stockId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Stock> getStockInfoById(@PathVariable(value="id", required=true) Long stockId){
		return ResponseEntity.ok(stockInfoService.getById(stockId));
	}


	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<Stock>> getStockInfoByIds(@RequestBody List<Long> stockIds){
		return ResponseEntity.ok(stockInfoService.getByIds(stockIds));
	}

	 
	@RequestMapping(value="/warehouse/{warehouseId}", method=RequestMethod.GET)
	public ResponseEntity<List<StockDto>> getStockInfoByWarehouseId(@PathVariable(value="warehouseId", required=true) Long warehouseId){
		return ResponseEntity.ok(stockInfoService.getByWarehouseId(warehouseId));
	}

	@RequestMapping(value="/warehouse/warehouse-ids", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteStockInfoByWarehouseIds(@RequestBody List<Long> warehouseIds){
		System.err.println();
		return ResponseEntity.ok(stockInfoService.deleteByWarehouseIds(warehouseIds));
	}

	@RequestMapping(value="/warehouse/{warehouseId}/material/{materialId}", method=RequestMethod.GET)
	public ResponseEntity<Stock> getStockInfoByWarehouseIdAndMaterialId(@PathVariable(value="warehouseId", required=true) Long warehouseId, @PathVariable(value="materialId", required=true) Long materialId){
		return ResponseEntity.ok(stockInfoService.getByWarehouseIdAndMaterialId(warehouseId, materialId));
	}

	@RequestMapping(value="/plant/{plantId}", method=RequestMethod.GET)
	public ResponseEntity<List<StockDto>> getStockInfoByPlant(@PathVariable(value="plantId", required=true) Long plantId){
		return ResponseEntity.ok(stockInfoService.getByPlantId(plantId));
	}
	
}
