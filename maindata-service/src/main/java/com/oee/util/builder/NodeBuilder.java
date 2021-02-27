package com.oee.util.builder;

import com.oee.entity.HierarchyEntity;
import com.oee.entity.NodeEntity;
import com.oee.enums.NodeType;

import java.util.ArrayList;

public abstract class NodeBuilder<SELF extends NodeBuilder<SELF, T>, T> implements Builder<T>{

    protected NodeEntity node = new NodeEntity();

    protected NodeBuilder nodeName(String name) {
        node.setName(name);
        return this;
    }

    protected NodeBuilder withParentNode(NodeEntity node) {
        if(node.getId() == null){
            throw new RuntimeException(node.getName() + " NodeID bos olamaz.");
        }
        this.node.setParent(node);
        return this;
    }

    protected NodeBuilder addChild(NodeEntity node) {
        if(this.node.getChildren() == null) {
            this.node.setChildren(new ArrayList<>());
        }
        this.node.getChildren().add(node);
        return this;
    }

    protected NodeBuilder nodeType(NodeType nodeType) {
        this.node.setType(nodeType);
        return this;
    }

    public SELF hierarchy(HierarchyEntity hierarchy) {
        this.node.setHierarchy(hierarchy);
        return (SELF) this;
    }

}
