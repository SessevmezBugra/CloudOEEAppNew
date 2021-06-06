package com.oee.service.impl;

import com.oee.entity.HierarchyHeaderEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.HierarchyHeaderRepository;
import com.oee.service.HierarchyHeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HierarchyHeaderServiceImpl implements HierarchyHeaderService {

    private final HierarchyHeaderRepository repository;

    @Override
    public HierarchyHeaderEntity create(HierarchyHeaderEntity entity) {

        return repository.save(entity);
    }

    @Override
    public HierarchyHeaderEntity update(HierarchyHeaderEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public HierarchyHeaderEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hierarchy not found."));
    }

    @Override
    public List<HierarchyHeaderEntity> findAll() {
        return repository.findAll();
    }
}
