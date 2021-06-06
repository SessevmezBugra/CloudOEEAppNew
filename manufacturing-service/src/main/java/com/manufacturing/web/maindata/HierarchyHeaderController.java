package com.manufacturing.web.maindata;

import com.manufacturing.dto.maindata.HierarchyHeaderDto;
import com.manufacturing.service.maindata.HierarchyHeaderService;
import com.manufacturing.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.HierarchyHeaderCtrl.CTRL)
@RequiredArgsConstructor
public class HierarchyHeaderController {

    private final HierarchyHeaderService hierarchyHeaderService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<HierarchyHeaderDto> createHierarchyHeader(@RequestBody HierarchyHeaderDto hierarchyHeaderDto){
        return ResponseEntity.ok(hierarchyHeaderService.create(hierarchyHeaderDto));
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<HierarchyHeaderDto> updateHierarchyHeader(@RequestBody HierarchyHeaderDto hierarchyHeaderDto){
        return ResponseEntity.ok(hierarchyHeaderService.update(hierarchyHeaderDto));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity deleteHierarchyHeaderById(@PathVariable(value="id", required=true) Long hierarchyHeaderId){
        hierarchyHeaderService.deleteById(hierarchyHeaderId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<HierarchyHeaderDto> getHierarchyHeaderById(@PathVariable(value="id", required=true) Long hierarchyHeaderId){
        return ResponseEntity.ok(hierarchyHeaderService.findById(hierarchyHeaderId));
    }
}
