package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MACHINE")
public class Machine {

    @Id
    @Column(name = "MACHINE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineId;

    @JsonBackReference
    @ManyToOne(optional=true)
    @JoinColumn(name="PLANT_ID")
    private PlantInfo plant;

    @Column(name = "MACHINE_CODE")
    private String machineCode;

    @Column(name = "MACHINE_DESC")
    private String machineDesc;
}
