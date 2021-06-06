package com.oee.web;

import com.oee.entity.UserNodeMappingEntity;
import com.oee.service.UserNodeMappingService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.NodeUserMappingCtrl.CTRL)
@RequiredArgsConstructor
public class UserNodeMappingController {

    private UserNodeMappingService userNodeMappingService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<UserNodeMappingEntity> createNodeUserMapping(@RequestBody UserNodeMappingEntity nodeUserMappingEntity){
        return ResponseEntity.ok(userNodeMappingService.create(nodeUserMappingEntity));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<UserNodeMappingEntity> updateNodeUserMapping(@RequestBody UserNodeMappingEntity nodeUserMappingEntity){
        return ResponseEntity.ok(userNodeMappingService.update(nodeUserMappingEntity));
    }

}
