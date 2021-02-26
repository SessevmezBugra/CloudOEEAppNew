package com.oee.builder;

import com.oee.entity.HierarchyEntity;
import com.oee.enums.NodeType;

public class HierarchyBuilder implements Builder<HierarchyEntity> {

    private HierarchyEntity hierarchy = new HierarchyEntity();
    private boolean isGlobal = false;

    public HierarchyBuilder name(String name) {
        hierarchy.setName(name);
        return this;
    }

    public HierarchyBuilder type(NodeType type) {
        hierarchy.setType(type);
        return this;
    }

    public HierarchyBuilder isGlobal(Boolean isGlobal) {
        hierarchy.setIsGlobal(isGlobal);
        this.isGlobal = isGlobal;
        return this;
    }

    public HierarchyBuilder addChild(String name, NodeType type) {

        HierarchyEntity child = new HierarchyEntity();
        child.setName(name);
        child.setType(type);
        HierarchyEntity lastHierarchy = findNullHierarchy(this.hierarchy);
        lastHierarchy.setChild(child);
        return this;

    }

    @Override
    public HierarchyEntity build(){
        return hierarchy;
    }

    private HierarchyEntity findNullHierarchy(HierarchyEntity hierarchy) {
        if (hierarchy.getChild() != null) {
            return findNullHierarchy(hierarchy.getChild());
        }
        return hierarchy;
    }

//    public static void main(String[] args) {
//        HierarchyEntity entity = new HierarchyEntity();
//        HierarchyEntity entity1 = new HierarchyEntity();
//        HierarchyEntity entity2 = new HierarchyEntity();
//        HierarchyEntity entity3 = new HierarchyEntity();
//        HierarchyEntity entity4 = new HierarchyEntity();
//        HierarchyEntity entity5 = new HierarchyEntity();
//
//        entity.setName("TEST1");
//        entity1.setName("TEST2");
//        entity2.setName("TEST3");
//        entity3.setName("TEST4");
//        entity4.setName("TEST5");
//        entity5.setName("TEST6");
//
//        entity.setChild(entity1);
//        entity1.setChild(entity2);
//        entity2.setChild(entity3);
//        entity3.setChild(entity4);
//        entity4.setChild(entity5);
//
//        HierarchyBuilder hierarchyBuilder = new HierarchyBuilder();
//        hierarchyBuilder.findNullHierarchy(entity);
//
//    }

}
