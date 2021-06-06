package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.QualityTypeDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface QualityTypeService extends GenericService<QualityTypeDto, Long> {

    List<QualityTypeDto> findAll();

    List<QualityTypeDto> findByPlantId(Long id);
}
