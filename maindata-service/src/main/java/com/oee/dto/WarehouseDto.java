package com.oee.dto;

import com.oee.entity.PlantInfo;

public class WarehouseDto {

    private Long warehouseId;
    private String warehouseName;
    private Long plantId;

    public WarehouseDto() {

    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }
}
