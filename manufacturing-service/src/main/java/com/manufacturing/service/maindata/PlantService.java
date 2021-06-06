package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.PlantDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface PlantService extends GenericService<PlantDto, Long> {

    PlantDto createWithSourcePlant(PlantDto plantEntity);

    PlantDto updateSourcePlant(Long refPlantId, Long sourcePlantId);

    List<PlantDto> findAll();
}
