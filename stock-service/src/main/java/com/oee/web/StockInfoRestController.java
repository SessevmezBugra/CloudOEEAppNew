package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.StockInfo;
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
	public ResponseEntity<StockInfo> createStockInfo(@RequestBody StockInfo stockInfo){
		
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
		return ResponseEntity.ok(stockInfoService.create(stockInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<StockInfo> updateStockInfo(@RequestBody StockInfo stockInfo){
		return ResponseEntity.ok(stockInfoService.update(stockInfo));
	}
	
	@RequestMapping(value="/add",method=RequestMethod.PUT)
	public ResponseEntity<StockInfo> addStockInfo(@RequestBody StockInfo stockInfo){
		return ResponseEntity.ok(stockInfoService.addStock(stockInfo));
	}
	
	@RequestMapping(value="/extract",method=RequestMethod.PUT)
	public ResponseEntity<StockInfo> extractStockInfo(@RequestBody StockInfo stockInfo){
		return ResponseEntity.ok(stockInfoService.extractStock(stockInfo));
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteStockInfo(@PathVariable(value="id", required=true) Long stockId){
		return ResponseEntity.ok(stockInfoService.delete(stockId));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StockInfo> getStockInfoById(@PathVariable(value="id", required=true) Long stockId){
		return ResponseEntity.ok(stockInfoService.getById(stockId));
	}
	 
	@RequestMapping(value="/warehouse/{warehouseId}", method=RequestMethod.GET)
	public ResponseEntity<List<StockInfo>> getStockInfoByWarehouseId(@PathVariable(value="warehouseId", required=true) Long warehouseId){
		return ResponseEntity.ok(stockInfoService.getByWarehouseId(warehouseId));
	}
	
	
}
