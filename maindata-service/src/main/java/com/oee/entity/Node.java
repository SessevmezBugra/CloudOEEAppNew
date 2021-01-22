package com.oee.entity;

import com.oee.enums.NodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NODE")
public class Node {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name="PARENT_ID")
    private Node parent;

    @Column(name="NODE_NAME")
    private String name;

    @Column(name="NODE_TYPE")
    private NodeType type;

    @Column(name="IS_GLOBAL")
    private Boolean isGlobal;

    @OneToOne(mappedBy = "node")
    private Plant plant;

    @OneToOne(mappedBy = "node")
    private Grouping grouping;

    @OneToOne(mappedBy = "node")
    private WorkCenter workCenter;

    @OneToOne(mappedBy = "node")
    private Machine machine;

    @OneToOne(mappedBy = "node")
    private HierarchyTemplate template;

    @OneToOne(mappedBy = "node")
    private Structure structure;

}
