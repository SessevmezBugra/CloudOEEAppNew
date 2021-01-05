package com.oee.dto;


public class ResponsibleAreaDto {

    private Long id;
    private Long areaId;
    private String areaType;
    private UserEntityDto userEntity;

    public ResponsibleAreaDto() {
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntityDto getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntityDto userEntity) {
        this.userEntity = userEntity;
    }
}
