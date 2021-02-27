package com.oee.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "GROUPING")
public class GroupingEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private NodeEntity node;

    @Column(name = "GROUPING_NAME")
    private String name;

    @Column(name = "GROUPING_DESC")
    private String desc;

}
