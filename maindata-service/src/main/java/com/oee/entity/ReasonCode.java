package com.oee.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="REASON_CODE")
@Getter
@Setter
@NoArgsConstructor
public class ReasonCode {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional=true)
    @JoinColumn(name="PLANT_ID")
    private PlantInfo plant;

    @Column(name="REASON_CODE")
    private String reasonCode;

    @Column(name="REASON_DESC")
    private String reasonDesc;


}
