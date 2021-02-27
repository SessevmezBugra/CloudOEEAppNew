package com.oee.web;

import com.oee.entity.ReasonCodeEntity;
import com.oee.service.ReasonCodeService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ReasonCodeCtrl.CTRL)
@RequiredArgsConstructor
public class ReasonCodeController {

    private final ReasonCodeService reasonCodeService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<ReasonCodeEntity> createReasonCode(@RequestBody ReasonCodeEntity reasonCodeEntity){
        return ResponseEntity.ok(reasonCodeService.create(reasonCodeEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<ReasonCodeEntity> updateReasonCode(@RequestBody ReasonCodeEntity reasonCodeEntity){
        return ResponseEntity.ok(reasonCodeService.update(reasonCodeEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteReasonCode(@PathVariable(value="id", required=true) Long id){
        reasonCodeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ReasonCodeEntity>> getAllReasonCode(){
        return ResponseEntity.ok(reasonCodeService.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ReasonCodeEntity> getById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(reasonCodeService.findById(id));
    }

    @RequestMapping(value="/plant/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<ReasonCodeEntity>> getByPlantId(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(reasonCodeService.findByPlantId(id));
    }
}
