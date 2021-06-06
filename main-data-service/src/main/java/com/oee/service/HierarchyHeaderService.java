package com.oee.service;


import com.oee.entity.HierarchyHeaderEntity;
import com.oee.service.common.generic.GenericService;

import java.util.List;

public interface HierarchyHeaderService extends GenericService<HierarchyHeaderEntity, Long> {
    List<HierarchyHeaderEntity> findAll();
}
