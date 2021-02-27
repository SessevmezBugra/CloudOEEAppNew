package com.oee.service;

import com.oee.entity.QualityTypeEntity;
import com.oee.service.common.generic.GenericService;

import java.util.List;

public interface QualityTypeService extends GenericService<QualityTypeEntity, Long> {

    List<QualityTypeEntity> findAll();

    List<QualityTypeEntity> findByPlantId(Long id);
}
