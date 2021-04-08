package com.oee.service.impl;

import com.oee.entity.UserNodeMappingEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.UserNodeMappingRepository;
import com.oee.service.UserNodeMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNodeMappingServiceImpl implements UserNodeMappingService {

    private final UserNodeMappingRepository userNodeMappingRepository;

    @Override
    public UserNodeMappingEntity create(UserNodeMappingEntity entity) {
        return userNodeMappingRepository.save(entity);
    }

    @Override
    public UserNodeMappingEntity update(UserNodeMappingEntity entity) {
        return userNodeMappingRepository.save(entity);
    }

    @Override
    public void deleteById(UserNodeMappingEntity.Key id) {
        userNodeMappingRepository.deleteById(id);
    }

    @Override
    public UserNodeMappingEntity findById(UserNodeMappingEntity.Key id) {
        return userNodeMappingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu degerlerde bir entity bulunamadi."));
    }
}
