package com.oee.util.builder;

import com.oee.entity.EquipmentEntity;
import com.oee.entity.MachineEntity;

public class EquipmentBuilder implements Builder<EquipmentEntity>{

    private EquipmentEntity equipment = new EquipmentEntity();

    public EquipmentBuilder name(String name) {
        equipment.setName(name);
        return this;
    }

    public EquipmentBuilder desc(String desc) {
        equipment.setDesc(desc);
        return this;
    }

    public EquipmentBuilder parent(MachineEntity machine) {
        equipment.setMachine(machine);
        return this;
    }

    @Override
    public EquipmentEntity build() {
        return equipment;
    }

}
