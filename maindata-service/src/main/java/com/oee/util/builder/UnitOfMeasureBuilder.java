package com.oee.builder;

import com.oee.entity.UnitOfMeasureEntity;

public class UnitOfMeasureBuilder implements Builder<UnitOfMeasureEntity>{

    private UnitOfMeasureEntity entity = new UnitOfMeasureEntity();

    public UnitOfMeasureBuilder code(String code) {
        entity.setCode(code);
        return this;
    }

    public UnitOfMeasureBuilder desc(String desc) {
        entity.setDesc(desc);
        return this;
    }

    @Override
    public UnitOfMeasureEntity build() {
        return entity;
    }


}
