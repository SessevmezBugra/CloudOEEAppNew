package com.oee.web;

import com.oee.entity.EquipmentEntity;
import com.oee.service.EquipmentService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.EquipmentCtrl.CTRL)
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<EquipmentEntity> createEquipment(@RequestBody EquipmentEntity equipmentEntity){
        return ResponseEntity.ok(equipmentService.create(equipmentEntity));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<EquipmentEntity> updateEquipment(@RequestBody EquipmentEntity equipmentEntity){
        return ResponseEntity.ok(equipmentService.update(equipmentEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentById(@PathVariable(value="id", required=true) Long id){
        equipmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity getEquipmentById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(equipmentService.findById(id));
    }
}
