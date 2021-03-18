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
@Table(name = "HIERARCHY")
public class HierarchyEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")
    private HierarchyEntity child;

    @Column(name="NAME")
    private String name;

    @Column(name="NODE_TYPE")
    private NodeType type;

    @Column(name="IS_GLOBAL")
    private Boolean isGlobal;

    @OneToMany(mappedBy="hierarchy", fetch = FetchType.LAZY)
    private List<NodeEntity> nodes;

}
