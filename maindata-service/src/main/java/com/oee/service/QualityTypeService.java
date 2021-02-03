package com.oee.service;

import com.oee.entity.QualityTypeEntity;

import java.util.List;

public interface QualityTypeService {

    QualityTypeEntity create(QualityTypeEntity qualityTypeEntity);

    QualityTypeEntity update(QualityTypeEntity qualityTypeEntity);

    Boolean deleteById(Long id);

    QualityTypeEntity findById(Long id);

    List<QualityTypeEntity> findAll();

    List<QualityTypeEntity> findByPlantId(Long id);
}
