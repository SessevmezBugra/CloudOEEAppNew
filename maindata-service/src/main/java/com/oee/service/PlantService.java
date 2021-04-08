package com.oee.service;

import com.oee.entity.PlantEntity;
import com.oee.service.common.generic.GenericService;

import java.util.List;

public interface PlantService extends GenericService<PlantEntity, Long> {

    PlantEntity createWithSourcePlant(PlantEntity plantEntity);

    PlantEntity updateSourcePlant(Long refPlantId, Long sourcePlantId);

    List<PlantEntity> findAll();
}
