package com.oee.service;

import com.oee.entity.QualityType;

import java.util.List;

public interface QualityTypeService {

    QualityType create(QualityType qualityType);

    QualityType update(QualityType qualityType);

    Boolean deleteById(Long id);

    QualityType findById(Long id);

    List<QualityType> findAll();

    List<QualityType> findByPlantId(Long id);
}
