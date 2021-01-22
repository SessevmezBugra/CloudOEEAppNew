package com.oee.web;

import com.oee.entity.ReasonCode;
import com.oee.service.ReasonCodeService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ReasonCodeCtrl.CTRL)
@RequiredArgsConstructor
public class ReasonCodeRestController {

    private final ReasonCodeService reasonCodeService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<ReasonCode> createReasonCode(@RequestBody ReasonCode reasonCode){
        return ResponseEntity.ok(reasonCodeService.create(reasonCode));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<ReasonCode> updateReasonCode(@RequestBody ReasonCode reasonCode){
        return ResponseEntity.ok(reasonCodeService.update(reasonCode));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteReasonCode(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(reasonCodeService.deleteById(id));
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ReasonCode>> getAllReasonCode(){
        return ResponseEntity.ok(reasonCodeService.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ReasonCode> getById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(reasonCodeService.findById(id));
    }

    @RequestMapping(value="/plant/{id}", method=RequestMethod.GET)
    public ResponseEntity<List<ReasonCode>> getByPlantId(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(reasonCodeService.findByPlantId(id));
    }
}
