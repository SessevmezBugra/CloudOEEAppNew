package com.oee.builder;

import com.oee.entity.PlantEntity;
import com.oee.entity.ReasonCodeEntity;
import com.oee.enums.DataElementType;

public class ReasonCodeBuilder implements Builder<ReasonCodeEntity>{

    private ReasonCodeEntity entity = new ReasonCodeEntity();

    public ReasonCodeBuilder desc(String desc) {
        entity.setDesc(desc);
        return this;
    }

    public ReasonCodeBuilder code(String code) {
        entity.setCode(code);
        return this;
    }

    public ReasonCodeBuilder type(DataElementType type) {
        entity.setType(type);
        return this;
    }

    public ReasonCodeBuilder parent(PlantEntity plant) {
        entity.setPlant(plant);
        return this;
    }

    @Override
    public ReasonCodeEntity build() {
        return entity;
    }
}
