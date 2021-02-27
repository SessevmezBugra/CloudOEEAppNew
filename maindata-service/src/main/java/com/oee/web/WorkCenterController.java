package com.oee.web;

import com.oee.entity.WorkCenterEntity;
import com.oee.service.WorkCenterService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.MachineCtrl.CTRL)
@RequiredArgsConstructor
public class WorkCenterController {

    private final WorkCenterService workCenterService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<WorkCenterEntity> createWorkCenter(@RequestBody WorkCenterEntity workCenterEntity){
        return ResponseEntity.ok(workCenterService.create(workCenterEntity));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<WorkCenterEntity> updateWorkCenter(@RequestBody WorkCenterEntity workCenterEntity){
        return ResponseEntity.ok(workCenterService.update(workCenterEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity deleteWorkCenterById(@PathVariable(value="id", required=true) Long id){
        workCenterService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity getWorkCenterById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(workCenterService.findById(id));
    }
}
