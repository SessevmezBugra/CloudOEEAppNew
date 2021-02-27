package com.oee.util.builder;

import com.oee.entity.WorkCenterEntity;
import com.oee.enums.NodeType;

public class WorkCenterBuilder extends NodeBuilder<WorkCenterBuilder, WorkCenterEntity>{

    private WorkCenterEntity workCenter = new WorkCenterEntity();

    {
        workCenter.setNode(node);
        nodeType(NodeType.WORK_CTR);
    }

    public WorkCenterBuilder name(String name){
        nodeName(name);
        this.workCenter.setName(name);
        return this;
    }


    public WorkCenterBuilder desc(String desc){
        this.workCenter.setDesc(desc);
        return this;
    }


    @Override
    public WorkCenterEntity build() {
        return this.workCenter;
    }
}
