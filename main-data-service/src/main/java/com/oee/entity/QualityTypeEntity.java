package com.oee.entity;


import com.oee.enums.DataElementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="QUALITY_TYPE")
@Getter
@Setter
@NoArgsConstructor
public class QualityTypeEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="PLANT_ID")
    private PlantEntity plant;

    @Column(name="QUALITY_NAME")
    private String name;

    @Column(name="DATA_ELEMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private DataElementType type;


}
