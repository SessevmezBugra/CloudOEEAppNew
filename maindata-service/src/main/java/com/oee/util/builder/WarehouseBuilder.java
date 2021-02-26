package com.oee.builder;

import com.oee.entity.PlantEntity;
import com.oee.entity.WarehouseEntity;

public class WarehouseBuilder implements Builder<WarehouseEntity>{

    private WarehouseEntity entity = new WarehouseEntity();

    public WarehouseBuilder name(String name) {
        entity.setName(name);
        return this;
    }

    public WarehouseBuilder parent(PlantEntity plant) {
        entity.setPlant(plant);
        return this;
    }

    @Override
    public WarehouseEntity build() {
        return entity;
    }
}
