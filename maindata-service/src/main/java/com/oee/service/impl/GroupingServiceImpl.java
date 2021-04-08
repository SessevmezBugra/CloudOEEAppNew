package com.oee.service.impl;

import com.oee.entity.GroupingEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.GroupingRepository;
import com.oee.service.GroupingService;
import com.oee.util.builder.GroupingBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupingServiceImpl implements GroupingService {

    private final GroupingRepository groupingRepository;

    @Override
    public GroupingEntity create(GroupingEntity entity) {
        GroupingEntity groupingEntity = new GroupingBuilder().name(entity.getName()).build();
        return groupingRepository.save(groupingEntity);
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
