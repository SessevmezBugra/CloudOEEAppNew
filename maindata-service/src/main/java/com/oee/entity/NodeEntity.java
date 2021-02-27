package com.oee.entity;

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

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    private NodeEntity parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
    private List<NodeEntity> children;

    @Column(name="NODE_NAME")
    private String name;

    @Column(name="NODE_TYPE")
    private NodeType type;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="HIERARCHY_ID")
    private HierarchyEntity hierarchy;

}
