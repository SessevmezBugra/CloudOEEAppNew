package com.manufacturing.service.maindata;

import com.manufacturing.dto.maindata.WarehouseDto;
import com.manufacturing.service.generic.GenericService;

import java.util.List;

public interface WarehouseService extends GenericService<WarehouseDto, Long> {

    List<WarehouseDto> findByIds(List<Long> warehouseIds);
}
