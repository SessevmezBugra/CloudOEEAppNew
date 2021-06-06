package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "USER_PLANT_MAPPING")
@IdClass(UserPlantMappingEntity.Key.class)
public class UserPlantMappingEntity implements Serializable {

    @Id
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLANT_ID")
    private PlantEntity plant;

    @Id
    @JoinColumn(name = "USERNAME")
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected PlantEntity plant;

        protected String username;

    }
}
