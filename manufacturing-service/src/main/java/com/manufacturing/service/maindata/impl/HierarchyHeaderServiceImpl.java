package com.manufacturing.service.maindata.impl;

import com.manufacturing.client.MainDataServiceClient;
import com.manufacturing.dto.maindata.HierarchyHeaderDto;
import com.manufacturing.service.maindata.HierarchyHeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HierarchyHeaderServiceImpl implements HierarchyHeaderService {

    private final MainDataServiceClient mainDataServiceClient;

    @Override
    public HierarchyHeaderDto create(HierarchyHeaderDto entity) {
        return mainDataServiceClient.createHierarchyHeader(entity).getBody();
    }

    @Override
    public HierarchyHeaderDto update(HierarchyHeaderDto entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public HierarchyHeaderDto findById(Long id) {
        return null;
    }
}
