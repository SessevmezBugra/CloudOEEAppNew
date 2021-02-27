package com.oee.service.impl;

import com.oee.entity.WorkCenterEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.WorkCenterRepository;
import com.oee.service.WorkCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkCenterServiceImpl implements WorkCenterService {

    private final WorkCenterRepository workCenterRepository;

    @Override
    public WorkCenterEntity create(WorkCenterEntity entity) {
        return workCenterRepository.save(entity);
    }

    @Override
    public WorkCenterEntity update(WorkCenterEntity entity) {
        return workCenterRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        workCenterRepository.deleteById(id);
    }

    @Override
    public WorkCenterEntity findById(Long id) {
        return workCenterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu is merkezi bulunamadi."));
    }
}
