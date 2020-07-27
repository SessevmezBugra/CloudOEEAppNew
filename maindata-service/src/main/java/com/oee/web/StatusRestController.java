package com.oee.web;

import com.oee.entity.OrderStatus;
import com.oee.service.StatusService;
import com.oee.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.StatusCtrl.CTRL)
public class StatusRestController {

    private final StatusService statusService;

    public StatusRestController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<OrderStatus> createStatus(@RequestBody OrderStatus orderStatus) {
        return ResponseEntity.ok(statusService.create(orderStatus));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<OrderStatus> updateStatus(@RequestBody OrderStatus orderStatus) {
        return ResponseEntity.ok(statusService.create(orderStatus));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteStatusById(@PathVariable(value="id", required=true) Long id) {
        return ResponseEntity.ok(statusService.deleteById(id));
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<OrderStatus>> getAll() {
        return ResponseEntity.ok(statusService.findAll());
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<OrderStatus> getById(@PathVariable(value="id", required=true) Long id) {
        return ResponseEntity.ok(statusService.findById(id));
    }

}
