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
@Table(name = "TEMPLATE")
public class HierarchyTemplate {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private Node node;

    @Column(name="TEMPLATE_NAME")
    private String name;

    @Column(name="IS_GLOBAL")
    private Boolean isGlobal;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, mappedBy="template")
    private List<Structure> structures;
}
