package com.oee.dto;


import java.util.List;

public class ClientDto {

    private Long clientId;
    private String clientName;
    private List<PlantDto> plants;

    public ClientDto() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<PlantDto> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDto> plants) {
        this.plants = plants;
    }
}
