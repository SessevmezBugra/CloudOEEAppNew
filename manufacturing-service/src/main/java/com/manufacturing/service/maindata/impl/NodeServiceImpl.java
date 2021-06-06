package com.manufacturing.service.maindata.impl;

import com.manufacturing.client.MainDataServiceClient;
import com.manufacturing.dto.maindata.NodeDto;
import com.manufacturing.service.maindata.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final MainDataServiceClient mainDataServiceClient;

    @Override
    public NodeDto create(NodeDto entity) {
        return null;
    }

    @Override
    public NodeDto update(NodeDto entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public NodeDto findById(Long id) {
        return null;
    }

    @Override
    public NodeDto updateParent(Long childNodeId, Long parentNodeId) {
        return null;
    }

    @Override
    public List<NodeDto> findByUsername(String username) {
        return mainDataServiceClient.getNodesByUsername(username).getBody();
    }
}
