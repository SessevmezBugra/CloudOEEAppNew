package com.oee.builder;

import com.oee.entity.PlantEntity;
import com.oee.entity.QualityTypeEntity;
import com.oee.enums.DataElementType;

public class QualityTypeBuilder implements Builder<QualityTypeEntity>{

    private QualityTypeEntity entity = new QualityTypeEntity();

    public QualityTypeBuilder name(String name) {
        entity.setName(name);
        return this;
    }

    public QualityTypeBuilder type(DataElementType type) {
        entity.setType(type);
        return this;
    }

    public QualityTypeBuilder parent(PlantEntity plant) {
        entity.setPlant(plant);
        return this;
    }

    @Override
    public QualityTypeEntity build() {
        return entity;
    }
}
