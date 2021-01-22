package com.oee.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WORK_CENTER")
public class WorkCenter {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private Node node;

    @Column(name = "WORK_CENTER_NAME")
    private String name;

    @Column(name = "WORK_CENTER_DESC")
    private String desc;

}
