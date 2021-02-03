package com.oee.service;

import com.oee.entity.MachineEntity;

public interface MachineService {

    MachineEntity create(MachineEntity machineEntity);

    MachineEntity update(MachineEntity machineEntity);

    Boolean delete(Long machineId);

    MachineEntity getById(Long machineId);

}
