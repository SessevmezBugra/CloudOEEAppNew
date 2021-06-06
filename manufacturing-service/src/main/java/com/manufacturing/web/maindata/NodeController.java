package com.manufacturing.web.maindata;

import com.manufacturing.config.CurrentUserProvider;
import com.manufacturing.dto.maindata.NodeDto;
import com.manufacturing.service.maindata.NodeService;
import com.manufacturing.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.NodeCtrl.CTRL)
@RequiredArgsConstructor
public class NodeController {

    private final NodeService nodeService;
    private final CurrentUserProvider currentUserProvider;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<NodeDto> createNode(@RequestBody NodeDto nodeDto){
        return ResponseEntity.ok(nodeService.create(nodeDto));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<NodeDto> updateNode(@RequestBody NodeDto nodeDto){
        return ResponseEntity.ok(nodeService.update(nodeDto));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity deleteNodeById(@PathVariable(value="id", required=true) Long id){
        nodeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<NodeDto> getNodeById(@PathVariable(value="id", required=true) Long id){
        return ResponseEntity.ok(nodeService.findById(id));
    }

    @RequestMapping(value="/username", method= RequestMethod.GET)
    public ResponseEntity<List<NodeDto>> getNodesByUsername(){
        System.out.println(currentUserProvider.getCurrentUser().getUsername());
        return ResponseEntity.ok(nodeService.findByUsername(currentUserProvider.getCurrentUser().getUsername()));
    }


}
