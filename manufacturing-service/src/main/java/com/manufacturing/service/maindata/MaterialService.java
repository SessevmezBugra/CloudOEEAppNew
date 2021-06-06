package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.MaterialDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface MaterialService extends GenericService<MaterialDto, Long> {

    List<MaterialDto> findMaterialsByIds(List<Long> ids);
}
