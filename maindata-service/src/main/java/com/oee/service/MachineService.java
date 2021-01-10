package com.oee.service;

import com.oee.entity.Machine;

import java.util.List;

public interface MachineService {

    Machine create(Machine machine);

    Machine update(Machine machine);

    Boolean delete(Long machineId);

    Machine getById(Long machineId);

    List<Machine> getByPlantId(Long plantId);
}
