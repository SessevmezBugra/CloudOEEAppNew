package com.oee.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.entity.ResponsibleArea;
import com.oee.service.ResponsibleAreaService;
import com.oee.util.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ResponsibleAreaCtrl.CTRL)
public class ResponsibleAreaRestController {
	
	@Autowired
	private ResponsibleAreaService responsibleAreaService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponsibleArea> createResponsibleArea(@RequestBody ResponsibleArea responsibleArea){
		return ResponseEntity.ok(responsibleAreaService.create(responsibleArea));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ResponsibleArea> updateResponsibleArea(@RequestBody ResponsibleArea responsibleArea){
		return ResponseEntity.ok(responsibleAreaService.update(responsibleArea));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteResponsibleArea(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(responsibleAreaService.delete(id));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ResponsibleArea> getResponsibleArea(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(responsibleAreaService.getById(id));
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public ResponseEntity<List<ResponsibleArea>> getResponsibleArea(@PathVariable(value="username", required=true) String username){
		return ResponseEntity.ok(responsibleAreaService.getByUsername(username));
	}
}
