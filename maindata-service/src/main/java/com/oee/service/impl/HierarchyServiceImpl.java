package com.oee.service.impl;

import com.oee.entity.HierarchyEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.HierarchyRepository;
import com.oee.service.HierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HierarchyServiceImpl implements HierarchyService {

    private final HierarchyRepository hierarchyRepository;

    @Override
    public HierarchyEntity create(HierarchyEntity entity) {
        return hierarchyRepository.save(entity);
    }

    @Override
    public HierarchyEntity update(HierarchyEntity entity) {
        return hierarchyRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        hierarchyRepository.deleteById(id);
    }

    @Override
    public HierarchyEntity findById(Long id) {
        return hierarchyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu hiyerarsi bulunamadi."));
    }
}
