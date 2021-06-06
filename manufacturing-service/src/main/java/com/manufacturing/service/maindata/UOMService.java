package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.UnitOfMeasureDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface UOMService extends GenericService<UnitOfMeasureDto, Long> {
	
	List<UnitOfMeasureDto> findAll();
}
