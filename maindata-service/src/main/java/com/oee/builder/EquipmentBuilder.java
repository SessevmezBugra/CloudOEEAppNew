package com.oee.builder;

import com.oee.entity.EquipmentEntity;
import com.oee.entity.MachineEntity;

public class EquipmentBuilder {

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

    public EquipmentEntity build() {
        return equipment;
    }

}
