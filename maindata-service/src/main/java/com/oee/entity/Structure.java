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
@Table(name = "STRUCTURE")
public class Structure {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private Node node;

    @JsonBackReference
    @ManyToOne(optional=true)
    @JoinColumn(name = "TEMPLATE_ID", referencedColumnName = "ID")
    private HierarchyTemplate template;

}
