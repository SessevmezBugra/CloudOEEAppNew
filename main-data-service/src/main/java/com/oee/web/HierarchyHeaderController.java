package com.oee.web;

import com.oee.entity.HierarchyHeaderEntity;
import com.oee.service.HierarchyHeaderService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.HierarchyHeaderCtrl.CTRL)
@RequiredArgsConstructor
public class HierarchyHeaderController {

    private final HierarchyHeaderService hierarchyHeaderService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<HierarchyHeaderEntity> createHierarchyHeader(@RequestBody HierarchyHeaderEntity hierarchyHeaderEntity){
        return ResponseEntity.ok(hierarchyHeaderService.create(hierarchyHeaderEntity));
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<HierarchyHeaderEntity>> getAllHierarchyHeader(){
        return ResponseEntity.ok(hierarchyHeaderService.findAll());
    }
}
