package com.oee.web;


import com.oee.entity.MachineEntity;
import com.oee.service.MachineService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.MachineCtrl.CTRL)
@RequiredArgsConstructor
public class MachineRestController {

    private final MachineService machineService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<MachineEntity> createMachine(@RequestBody MachineEntity machineEntity){
        return ResponseEntity.ok(machineService.create(machineEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<MachineEntity> updateMachine(@RequestBody MachineEntity machineEntity){
        return ResponseEntity.ok(machineService.update(machineEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMachine(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(machineService.delete(id));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<MachineEntity> getById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(machineService.getById(id));
    }
}
