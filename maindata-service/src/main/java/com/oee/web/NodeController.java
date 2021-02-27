package com.oee.web;

import com.oee.entity.GroupingEntity;
import com.oee.entity.NodeEntity;
import com.oee.service.NodeService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.NodeCtrl.CTRL)
@RequiredArgsConstructor
public class NodeController {

    private final NodeService nodeService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<NodeEntity> createNode(@RequestBody NodeEntity nodeEntity){
        return ResponseEntity.ok(nodeService.create(nodeEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<NodeEntity> updateNode(@RequestBody NodeEntity nodeEntity){
        return ResponseEntity.ok(nodeService.update(nodeEntity));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity deleteNodeById(@PathVariable(value="id", required=true) Long id){
        nodeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity getNodeById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(nodeService.findById(id));
    }

}
