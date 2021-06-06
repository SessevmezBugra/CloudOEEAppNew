package com.oee.web;


import com.oee.entity.QualityTypeEntity;
import com.oee.service.QualityTypeService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.QualityTypeCtrl.CTRL)
@RequiredArgsConstructor
public class QualityTypeController {

    private final QualityTypeService qualityTypeService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<QualityTypeEntity> createQualityType(@RequestBody QualityTypeEntity qualityTypeEntity){
        return ResponseEntity.ok(qualityTypeService.create(qualityTypeEntity));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<QualityTypeEntity> updateQualityType(@RequestBody QualityTypeEntity qualityTypeEntity){
        return ResponseEntity.ok(qualityTypeService.update(qualityTypeEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteQualityTypeById(@PathVariable(value="id", required=true) Long id){
        qualityTypeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<QualityTypeEntity>> getAllQualityType(){
        return ResponseEntity.ok(qualityTypeService.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<QualityTypeEntity> getQualityTypeById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(qualityTypeService.findById(id));
    }

    @RequestMapping(value="/plant/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<QualityTypeEntity>> getQualityTypeByPlantId(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(qualityTypeService.findByPlantId(id));
    }
}
