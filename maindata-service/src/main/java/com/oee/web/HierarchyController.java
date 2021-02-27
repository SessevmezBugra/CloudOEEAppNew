package com.oee.web;


import com.oee.entity.HierarchyEntity;
import com.oee.service.HierarchyService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.HierarchyCtrl.CTRL)
@RequiredArgsConstructor
public class HierarchyController {

    private final HierarchyService hierarchyService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<HierarchyEntity> createHierarchy(@RequestBody HierarchyEntity hierarchyEntity){
        return ResponseEntity.ok(hierarchyService.create(hierarchyEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<HierarchyEntity> updateHierarchy(@RequestBody HierarchyEntity hierarchyEntity){
        return ResponseEntity.ok(hierarchyService.update(hierarchyEntity));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<HierarchyEntity> deleteHierarchyById(@PathVariable(value="id", required=true) Long id){
        hierarchyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<HierarchyEntity> findHierarchyById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(hierarchyService.findById(id));
    }
}
