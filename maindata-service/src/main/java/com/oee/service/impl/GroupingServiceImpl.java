package com.oee.service.impl;

import com.oee.entity.GroupingEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.GroupingRepository;
import com.oee.service.GroupingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupingServiceImpl implements GroupingService {

    private final GroupingRepository groupingRepository;

    @Override
    public GroupingEntity create(GroupingEntity entity) {
        return groupingRepository.save(entity);
    }

    @Override
    public GroupingEntity update(GroupingEntity entity) {
        return groupingRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        groupingRepository.deleteById(id);
    }

    @Override
    public GroupingEntity findById(Long id) {
        return groupingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu grup bulunamadi."));
    }
}
