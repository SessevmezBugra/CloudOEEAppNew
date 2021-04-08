package com.oee.util.builder;

import com.oee.entity.*;
import com.oee.enums.NodeType;

import java.util.ArrayList;

public class PlantBuilder extends NodeBuilder<PlantBuilder, PlantEntity>{

    private PlantEntity plant = new PlantEntity();

    {
        plant.setNode(node);
        nodeType(NodeType.PLANT);
    }

    public PlantBuilder name(String name) {
        nodeName(name);
        this.plant.setName(name);
        return this;
    }

    public PlantBuilder sourcePlant(PlantEntity sourcePlant) {
        this.plant.setSourcePlant(sourcePlant);
        return this;
    }

    public PlantBuilder addUser(String username) {
        UserPlantMappingEntity userPlantMappingEntity = new UserPlantMappingEntity();
        userPlantMappingEntity.setUsername(username);
        userPlantMappingEntity.setPlant(this.plant);
        if(plant.getUsers() == null) {
            plant.setUsers(new ArrayList<>());
        }
        plant.getUsers().add(userPlantMappingEntity);
        return this;
    }

    public PlantBuilder addMaterial(MaterialEntity material) {
        if(plant.getMaterials() == null) {
            plant.setMaterials(new ArrayList<>());
        }
        plant.getMaterials().add(material);
        return this;
    }

    public PlantBuilder addWarehouse(WarehouseEntity warehouse) {
        if(plant.getWarehouses() == null) {
            plant.setWarehouses(new ArrayList<>());
        }
        plant.getWarehouses().add(warehouse);
        return this;
    }

    public PlantBuilder addReasonCode(ReasonCodeEntity reasonCode) {
        if(plant.getReasonCodes() == null) {
            plant.setReasonCodes(new ArrayList<>());
        }
        plant.getReasonCodes().add(reasonCode);
        return this;
    }

    public PlantBuilder addQualityType(QualityTypeEntity qualityType) {
        if(plant.getQualityTypes() == null) {
            plant.setQualityTypes(new ArrayList<>());
        }
        plant.getQualityTypes().add(qualityType);
        return this;
    }

    @Override
    public PlantEntity build() {
        return plant;
    }
}
