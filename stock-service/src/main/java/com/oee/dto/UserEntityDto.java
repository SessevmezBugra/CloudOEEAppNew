package com.oee.dto;

import java.util.List;

public class UserEntityDto {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private List<ResponsibleAreaDto> responsibleAreas;

    public UserEntityDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ResponsibleAreaDto> getResponsibleAreas() {
        return responsibleAreas;
    }

    public void setResponsibleAreas(List<ResponsibleAreaDto> responsibleAreas) {
        this.responsibleAreas = responsibleAreas;
    }
}
