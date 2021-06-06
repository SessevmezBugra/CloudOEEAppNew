package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oee.enums.NodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "NODE")
public class NodeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    private NodeEntity parent;

    @JsonManagedReference
    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
    private List<NodeEntity> children;

    @OneToMany(mappedBy="node", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserNodeMappingEntity> users;

    @Column(name="NODE_NAME")
    private String name;

    @Column(name="NODE_TYPE")
    private NodeType type;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="HIERARCHY_ID")
    private HierarchyEntity hierarchy;

}
