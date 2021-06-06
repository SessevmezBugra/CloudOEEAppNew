package com.oee.web;

import com.oee.entity.GroupingEntity;
import com.oee.service.GroupingService;
import com.oee.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.GroupingCtrl.CTRL)
@RequiredArgsConstructor
public class GroupingController {

    private final GroupingService groupingService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<GroupingEntity> createGrouping(@RequestBody GroupingEntity groupingEntity){
        return ResponseEntity.ok(groupingService.create(groupingEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<GroupingEntity> updateGrouping(@RequestBody GroupingEntity groupingEntity){
        return ResponseEntity.ok(groupingService.update(groupingEntity));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity deleteGroupingById(@PathVariable(value="id", required=true) Long id){
        groupingService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<GroupingEntity> findGroupingById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(groupingService.findById(id));
    }
}
