package com.oee.service;

import com.oee.entity.NodeEntity;
import com.oee.service.common.generic.GenericService;

public interface NodeService extends GenericService<NodeEntity, Long> {

    NodeEntity updateParent(Long childNodeId, Long parentNodeId);

}
