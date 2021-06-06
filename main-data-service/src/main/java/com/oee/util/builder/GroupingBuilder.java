package com.oee.util.builder;

import com.oee.entity.GroupingEntity;
import com.oee.enums.NodeType;

public class GroupingBuilder extends NodeBuilder<GroupingBuilder, GroupingEntity> {

    private GroupingEntity grouping = new GroupingEntity();

    {
        grouping.setNode(node);
        nodeType(NodeType.GROUPING);
    }

    public GroupingBuilder name(String name) {
        nodeName(name);
        this.grouping.setName(name);
        return this;
    }

    public GroupingBuilder desc(String desc) {
        this.grouping.setDesc(desc);
        return this;
    }


    @Override
    public GroupingEntity build() {
        return grouping;
    }
}
