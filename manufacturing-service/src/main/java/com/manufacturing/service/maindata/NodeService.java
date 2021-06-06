package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.NodeDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface NodeService extends GenericService<NodeDto, Long> {

    NodeDto updateParent(Long childNodeId, Long parentNodeId);

    List<NodeDto> findByUsername(String username);

}
