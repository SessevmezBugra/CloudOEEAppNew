package com.oee.web;

import com.oee.entity.NodeUserMappingEntity;
import com.oee.service.NodeUserMappingService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.NodeUserMappingCtrl.CTRL)
@RequiredArgsConstructor
public class NodeUserMappingController {

    private NodeUserMappingService nodeUserMappingService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<NodeUserMappingEntity> createNodeUserMapping(@RequestBody NodeUserMappingEntity nodeUserMappingEntity){
        return ResponseEntity.ok(nodeUserMappingService.create(nodeUserMappingEntity));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<NodeUserMappingEntity> updateNodeUserMapping(@RequestBody NodeUserMappingEntity nodeUserMappingEntity){
        return ResponseEntity.ok(nodeUserMappingService.update(nodeUserMappingEntity));
    }

}
