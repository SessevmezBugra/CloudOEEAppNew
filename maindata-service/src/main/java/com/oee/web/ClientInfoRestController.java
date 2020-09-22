package com.oee.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.ClientInfo;
import com.oee.service.ClientInfoService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ClientInfoCtrl.CTRL)
public class ClientInfoRestController {
	
	private final ClientInfoService clientInfoService;
	
	public ClientInfoRestController(ClientInfoService clientInfoService) {
		this.clientInfoService = clientInfoService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ClientInfo> createClientInfo(@RequestBody ClientInfo clientInfo){
		System.err.println("TEST");
		return ResponseEntity.ok(clientInfoService.create(clientInfo));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ClientInfo> updateClientInfo(@RequestBody ClientInfo clientInfo){
		return ResponseEntity.ok(clientInfoService.update(clientInfo));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteClientInfo(@PathVariable(value="id", required=true) Long clientId){
		return ResponseEntity.ok(clientInfoService.delete(clientId));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientInfo>> getClientsByLoggedUser(){
		return ResponseEntity.ok(clientInfoService.getClientsByLoggedUser());
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClientInfo> getClientInfoById(@PathVariable(value="id", required=true) Long clientId){
		return ResponseEntity.ok(clientInfoService.getById(clientId));
	}
	
	@RequestMapping(value="/company/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ClientInfo>> getClientInfoByCompanyId(@PathVariable(value="id", required=true) Long companyId){
		return ResponseEntity.ok(clientInfoService.getByCompanyId(companyId));
	}

	@RequestMapping(value="/ids", method=RequestMethod.POST)
	public ResponseEntity<List<ClientInfo>> getClientsByIds(@RequestBody List<Long> ids){
		return ResponseEntity.ok(clientInfoService.getClientsByIds(ids));
	}
}
