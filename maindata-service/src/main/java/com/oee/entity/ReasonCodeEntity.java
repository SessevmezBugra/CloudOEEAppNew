package com.oee.entity;


import com.oee.enums.DataElementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="REASON_CODE")
@Getter
@Setter
@NoArgsConstructor
public class ReasonCodeEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="PLANT_ID")
    private PlantEntity plant;

    @Enumerated(EnumType.STRING)
    @Column(name="DATA_ELEMENT_TYPE")
    private DataElementType type;

    @Column(name="REASON_CODE")
    private String code;

    @Column(name="REASON_DESC")
    private String desc;




}
