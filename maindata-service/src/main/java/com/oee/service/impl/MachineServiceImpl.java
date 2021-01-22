package com.oee.service.impl;

import com.oee.entity.Machine;
import com.oee.repository.MachineRepository;
import com.oee.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    @Override
    public Machine create(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public Machine update(Machine machine) {
        Machine foundedMachine = machineRepository.findById(machine.getId()).get();
        foundedMachine.setDesc(machine.getDesc());
        foundedMachine.setName(machine.getName());
        machineRepository.save(foundedMachine);
        return foundedMachine;
    }

    @Override
    public Boolean delete(Long machineId) {
        machineRepository.deleteById(machineId);
        return Boolean.TRUE;
    }

    @Override
    public Machine getById(Long machineId) {
        return machineRepository.findById(machineId).get();
    }

}
