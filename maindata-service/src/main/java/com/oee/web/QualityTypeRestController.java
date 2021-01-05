package com.oee.web;


import com.oee.entity.PlantInfo;
import com.oee.entity.QualityType;
import com.oee.service.QualityTypeService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.QualityTypeCtrl.CTRL)
@RequiredArgsConstructor
public class QualityTypeRestController {

    private final QualityTypeService qualityTypeService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<QualityType> createQualityType(@RequestBody QualityType qualityType){
        return ResponseEntity.ok(qualityTypeService.create(qualityType));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<QualityType> updateQualityType(@RequestBody QualityType qualityType){
        return ResponseEntity.ok(qualityTypeService.update(qualityType));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteQualityTypeById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(qualityTypeService.deleteById(id));
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<QualityType>> getAllQualityType(){
        return ResponseEntity.ok(qualityTypeService.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<QualityType> getQualityTypeById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(qualityTypeService.findById(id));
    }

    @RequestMapping(value="/plant/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<QualityType>> getQualityTypeByPlantId(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(qualityTypeService.findByPlantId(id));
    }
}
