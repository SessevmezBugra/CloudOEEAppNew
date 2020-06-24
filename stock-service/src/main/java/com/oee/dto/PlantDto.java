package com.oee.dto;


import java.util.List;

public class PlantDto {

    private Long plantId;
    private String plantName;
    private List<MaterialDto> materials;
    private List<WarehouseDto> warehouses;
    private ClientDto client;

    public PlantDto() {
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public List<MaterialDto> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialDto> materials) {
        this.materials = materials;
    }

    public List<WarehouseDto> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDto> warehouses) {
        this.warehouses = warehouses;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }
}
