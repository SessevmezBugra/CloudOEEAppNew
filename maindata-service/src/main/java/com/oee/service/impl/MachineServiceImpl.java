package com.oee.service.impl;

import com.oee.entity.MachineEntity;
import com.oee.repository.MachineRepository;
import com.oee.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    @Override
    public MachineEntity create(MachineEntity machineEntity) {
        return machineRepository.save(machineEntity);
    }

    @Override
    public MachineEntity update(MachineEntity machineEntity) {
        MachineEntity foundedMachineEntity = machineRepository.findById(machineEntity.getId()).get();
        foundedMachineEntity.setDesc(machineEntity.getDesc());
        foundedMachineEntity.setName(machineEntity.getName());
        machineRepository.save(foundedMachineEntity);
        return foundedMachineEntity;
    }

    @Override
    public Boolean delete(Long machineId) {
        machineRepository.deleteById(machineId);
        return Boolean.TRUE;
    }

    @Override
    public MachineEntity getById(Long machineId) {
        return machineRepository.findById(machineId).get();
    }

}
