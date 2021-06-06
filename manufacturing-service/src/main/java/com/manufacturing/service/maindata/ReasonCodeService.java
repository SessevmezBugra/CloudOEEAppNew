package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.ReasonCodeDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface ReasonCodeService extends GenericService<ReasonCodeDto, Long> {

    List<ReasonCodeDto> findAll();

    List<ReasonCodeDto> findByPlantId(Long id);
}
