package com.oee.util.builder;

import com.oee.entity.PlantEntity;
import com.oee.entity.UserWorkCenterMappingEntity;
import com.oee.entity.WorkCenterEntity;
import com.oee.enums.NodeType;

import java.util.ArrayList;

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

    public WorkCenterBuilder plant(PlantEntity plant){
        this.workCenter.setPlant(plant);
        return this;
    }

    public WorkCenterBuilder desc(String desc){
        this.workCenter.setDesc(desc);
        return this;
    }

    public WorkCenterBuilder addUser(String username) {
        UserWorkCenterMappingEntity userWorkCenterMappingEntity = new UserWorkCenterMappingEntity();
        userWorkCenterMappingEntity.setUsername(username);
        userWorkCenterMappingEntity.setWorkCenter(this.workCenter);
        if(workCenter.getUsers() == null) {
            workCenter.setUsers(new ArrayList<>());
        }
        workCenter.getUsers().add(userWorkCenterMappingEntity);
        return this;
    }


    @Override
    public WorkCenterEntity build() {
        return this.workCenter;
    }
}
