package com.oee.service.impl;

import com.oee.entity.EquipmentEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.EquipmentRepository;
import com.oee.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public EquipmentEntity create(EquipmentEntity entity) {
        return equipmentRepository.save(entity);
    }

    @Override
    public EquipmentEntity update(EquipmentEntity entity) {
        return equipmentRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public EquipmentEntity findById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu ekipman bulunamadi."));
    }

}
