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
@Table(name = "EQUIPMENT")
public class EquipmentEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name = "MACHINE_ID", referencedColumnName = "ID")
    private MachineEntity machine;

    @Column(name = "EQUIPMENT_NAME")
    private String name;

    @Column(name = "EQUIPMENT_DESC")
    private String desc;

}
