package com.oee.service.impl;

import com.oee.entity.NodeEntity;
import com.oee.entity.NodeUserMappingEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.NodeRepository;
import com.oee.repository.NodeUserMappingRepository;
import com.oee.service.NodeUserMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeUserMappingServiceImpl implements NodeUserMappingService {

    private final NodeUserMappingRepository nodeUserMappingRepository;

    @Override
    public NodeUserMappingEntity create(NodeUserMappingEntity entity) {
        return nodeUserMappingRepository.save(entity);
    }

    @Override
    public NodeUserMappingEntity update(NodeUserMappingEntity entity) {
        return nodeUserMappingRepository.save(entity);
    }

    @Override
    public void deleteById(NodeUserMappingEntity.Key id) {
        nodeUserMappingRepository.deleteById(id);
    }

    @Override
    public NodeUserMappingEntity findById(NodeUserMappingEntity.Key id) {
        return nodeUserMappingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu degerlerde bir entity bulunamadi."));
    }
}
