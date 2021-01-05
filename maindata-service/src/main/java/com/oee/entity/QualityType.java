package com.oee.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="QUALITY_TYPE")
@Getter
@Setter
@NoArgsConstructor
public class QualityType {

    @Id
    @Column(name="QUALITY_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long qualityId;

    @JsonBackReference
    @ManyToOne(optional=true)
    @JoinColumn(name="PLANT_ID")
    private PlantInfo plant;

    @Column(name="QUALITY_TYPE")
    private String qualityType;

    @Column(name="QUALITY_DESC")
    private String qualityDesc;


}
