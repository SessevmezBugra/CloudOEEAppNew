package com.oee.service;

import java.util.List;

import com.oee.entity.MaterialEntity;
import com.oee.service.common.generic.GenericService;

public interface MaterialService extends GenericService<MaterialEntity, Long> {

    List<MaterialEntity> findMaterialsByIds(List<Long> ids);
}
