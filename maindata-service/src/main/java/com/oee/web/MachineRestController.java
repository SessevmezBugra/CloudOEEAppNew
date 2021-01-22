package com.oee.web;


import com.oee.entity.Machine;
import com.oee.service.MachineService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.MachineCtrl.CTRL)
@RequiredArgsConstructor
public class MachineRestController {

    private final MachineService machineService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Machine> createMachine(@RequestBody Machine machine){
        return ResponseEntity.ok(machineService.create(machine));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<Machine> updateMachine(@RequestBody Machine machine){
        return ResponseEntity.ok(machineService.update(machine));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMachine(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(machineService.delete(id));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Machine> getById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(machineService.getById(id));
    }
}
