package com.oee.service;

import com.oee.entity.ReasonCodeEntity;

import java.util.List;

public interface ReasonCodeService {

    ReasonCodeEntity create(ReasonCodeEntity reasonCodeEntity);

    ReasonCodeEntity update(ReasonCodeEntity reasonCodeEntity);

    Boolean deleteById(Long id);

    ReasonCodeEntity findById(Long id);

    List<ReasonCodeEntity> findAll();

    List<ReasonCodeEntity> findByPlantId(Long id);
}
