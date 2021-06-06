package com.oee.service;

import com.oee.entity.ReasonCodeEntity;
import com.oee.service.common.generic.GenericService;

import java.util.List;

public interface ReasonCodeService extends GenericService<ReasonCodeEntity, Long> {

    List<ReasonCodeEntity> findAll();

    List<ReasonCodeEntity> findByPlantId(Long id);
}
