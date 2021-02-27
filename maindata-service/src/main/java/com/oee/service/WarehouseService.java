package com.oee.service;

import java.util.List;

import com.oee.entity.WarehouseEntity;
import com.oee.service.common.generic.GenericService;

public interface WarehouseService extends GenericService<WarehouseEntity, Long> {

    List<WarehouseEntity> findByIds(List<Long> warehouseIds);
}
