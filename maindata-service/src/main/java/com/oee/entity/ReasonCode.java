package com.oee.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oee.enums.ReasonCodeType;
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
    @Column(name="REASON_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reasonId;

    @JsonBackReference
    @ManyToOne(optional=true)
    @JoinColumn(name="PLANT_ID")
    private PlantInfo plant;

    @Column(name="REASON_TYPE")
    private ReasonCodeType reasonCodeType;

    @Column(name="REASON_CODE")
    private String reasonCode;

    @Column(name="REASON_DESC")
    private String reasonDesc;




}
