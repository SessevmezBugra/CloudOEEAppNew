package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="USER_ENTITY")
public class UserEntity {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="USERNAME")
    private String username;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.REMOVE, mappedBy="userEntity")
    private List<ResponsibleArea> responsibleAreas;

    public UserEntity() {
    }

//    public UserEntity(String id, String firstName, String lastName, String username) {
//    }

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

    public List<ResponsibleArea> getResponsibleAreas() {
        return responsibleAreas;
    }

    public void setResponsibleAreas(List<ResponsibleArea> responsibleAreas) {
        this.responsibleAreas = responsibleAreas;
    }
}
