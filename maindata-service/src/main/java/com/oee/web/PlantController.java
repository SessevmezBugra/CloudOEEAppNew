package com.oee.web;

import com.oee.entity.NodeEntity;
import com.oee.entity.PlantEntity;
import com.oee.enums.NodeType;
import com.oee.service.NodeService;
import com.oee.service.PlantService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.PlantCtrl.CTRL)
@RequiredArgsConstructor
public class PlantController {
	
	private final PlantService plantService;
	private final NodeService nodeService;

	@RequestMapping(value="/parent-node/{id}", method=RequestMethod.POST)
	public ResponseEntity<PlantEntity> createPlantAndAddToNode(@PathVariable(value="id", required=true) Long parentNodeId, @RequestBody PlantEntity plantEntity){

		PlantEntity refPlant = plantService.createWithSourcePlant(plantEntity);
		Long childNodeId = refPlant.getNode().getId();

		NodeEntity parentNode = nodeService.findById(parentNodeId);
		if (!NodeType.GROUPING.equals(parentNode.getType())) {
			throw new IllegalArgumentException("Parent has to be grouping.");
		}

		nodeService.updateParent(childNodeId, parentNodeId);

		return ResponseEntity.ok(refPlant);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<PlantEntity> updatePlantInfo(@RequestBody PlantEntity plantEntity){
		return ResponseEntity.ok(plantService.update(plantEntity));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePlantInfo(@PathVariable(value="id", required=true) Long plantId){
		plantService.deleteById(plantId);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PlantEntity> getPlantInfoById(@PathVariable(value="id", required=true) Long id){
		return ResponseEntity.ok(plantService.findById(id));
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PlantEntity>> getPlants(){
		return ResponseEntity.ok(plantService.findAll());
	}

}
