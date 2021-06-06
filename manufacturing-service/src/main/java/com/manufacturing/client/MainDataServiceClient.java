package com.manufacturing.client;

import com.manufacturing.config.ClientConfiguration;
import com.manufacturing.dto.maindata.HierarchyHeaderDto;
import com.manufacturing.dto.maindata.NodeDto;
import com.manufacturing.util.constant.client.MainDataClientApiPaths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "main-data-service", configuration = {ClientConfiguration.class})
public interface MainDataServiceClient {

    @RequestMapping(value = MainDataClientApiPaths.HierarchyHeaderCtrl.CTRL, method= RequestMethod.POST)
    public ResponseEntity<HierarchyHeaderDto> createHierarchyHeader(@RequestBody HierarchyHeaderDto hierarchyHeaderDto);

    @RequestMapping(value = MainDataClientApiPaths.HierarchyHeaderCtrl.CTRL, method= RequestMethod.GET)
    public ResponseEntity<List<HierarchyHeaderDto>> getAllHierarchyHeader();

    @RequestMapping(value = MainDataClientApiPaths.NodeCtrl.CTRL + "/user/{username}", method= RequestMethod.GET)
    public ResponseEntity<List<NodeDto>> getNodesByUsername(@PathVariable(value = "username") String username);

}