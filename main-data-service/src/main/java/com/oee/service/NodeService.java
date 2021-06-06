package com.oee.service;

import com.oee.entity.NodeEntity;
import com.oee.service.common.generic.GenericService;

import java.util.List;

public interface NodeService extends GenericService<NodeEntity, Long> {

    NodeEntity updateParent(Long childNodeId, Long parentNodeId);

    List<NodeEntity> findByUsername(String username);

}
