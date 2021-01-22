package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MACHINE")
public class Machine {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private Node node;

    @Column(name = "MACHINE_NAME")
    private String name;

    @Column(name = "MACHINE_DESC")
    private String desc;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, mappedBy="machine")
    private List<Equipment> equipments;
}
