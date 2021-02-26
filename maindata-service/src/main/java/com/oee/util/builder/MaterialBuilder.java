package com.oee.builder;

import com.oee.entity.MaterialEntity;

public class MaterialBuilder implements Builder<MaterialEntity>{

    private MaterialEntity material = new MaterialEntity();

    public MaterialBuilder name(String name) {
        material.setName(name);
        return this;
    }

    public MaterialBuilder desc(String desc) {
        material.setDesc(desc);
        return this;
    }

    public MaterialBuilder number(String number) {
        material.setNumber(number);
        return this;
    }

    @Override
    public MaterialEntity build() {
        return material;
    }

}
