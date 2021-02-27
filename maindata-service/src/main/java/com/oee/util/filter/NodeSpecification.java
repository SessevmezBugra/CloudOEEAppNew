package com.oee.util.filter;

import com.oee.entity.NodeEntity;
import com.oee.enums.NodeType;

class NodeTypeSpecification implements Specification<NodeEntity>{

    private NodeType type;

    public NodeTypeSpecification(NodeType type) {
        this.type = type;
    }

    @Override
    public boolean isSatisfied(NodeEntity item) {
        return type == item.getType();
    }
}

