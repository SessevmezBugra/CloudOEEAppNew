package com.oee.util.builder;

import com.oee.entity.MachineEntity;
import com.oee.entity.WorkCenterEntity;
import com.oee.enums.NodeType;

public class MachineBuilder extends NodeBuilder<MachineBuilder, MachineEntity> {

    private MachineEntity machine = new MachineEntity();

    {
        machine.setNode(node);
        nodeType(NodeType.MACHINE);
    }

    public MachineBuilder name(String name) {
        nodeName(name);
        this.machine.setName(name);
        return this;
    }

    public MachineBuilder desc(String desc) {
        this.machine.setDesc(desc);
        return this;
    }

    public MachineBuilder parent(WorkCenterEntity workCenterEntity) {
        withParentNode(workCenterEntity.getNode());
        return this;
    }

    @Override
    public MachineEntity build() {
        return this.machine;
    }
}
