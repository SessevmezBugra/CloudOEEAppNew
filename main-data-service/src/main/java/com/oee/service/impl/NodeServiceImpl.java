package com.oee.service.impl;

import com.oee.entity.NodeEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.NodeRepository;
import com.oee.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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
    public NodeEntity updateParent(Long childNodeId, Long parentNodeId) {
        if(ObjectUtils.isEmpty(childNodeId) || ObjectUtils.isEmpty(parentNodeId)) {
            throw new IllegalArgumentException("Node has to be not empty.");
        }
        NodeEntity childNode = findById(childNodeId);
        NodeEntity parentNode = findById(parentNodeId);
        childNode.setParent(parentNode);
        return nodeRepository.save(childNode);
    }

    @Override
    public List<NodeEntity> findByUsername(String username) {
        return nodeRepository.findByUsersUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        nodeRepository.deleteById(id);
    }

    @Override
    public NodeEntity findById(Long id) {
        return nodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Node not found."));
    }
}
