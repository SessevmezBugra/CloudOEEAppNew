package com.oee.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WORK_CENTER")
public class WorkCenterEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private NodeEntity node;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="PLANT_ID", referencedColumnName = "ID")
    private PlantEntity plant;

    @Column(name = "WORK_CENTER_NAME")
    private String name;

    @Column(name = "WORK_CENTER_DESC")
    private String desc;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="workCenter", fetch = FetchType.LAZY)
    private List<UserWorkCenterMappingEntity> users;

}
