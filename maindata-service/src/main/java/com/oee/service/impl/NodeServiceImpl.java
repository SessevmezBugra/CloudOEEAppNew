package com.oee.service.impl;

import com.oee.entity.NodeEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.NodeRepository;
import com.oee.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    @Override
    public NodeEntity create(NodeEntity entity) {
        return nodeRepository.save(entity);
    }

    @Override
    public NodeEntity update(NodeEntity entity) {
        return nodeRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        nodeRepository.deleteById(id);
    }

    @Override
    public NodeEntity findById(Long id) {
        return nodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bu malzeme bulunamadi."));
    }
}
