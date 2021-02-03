package com.oee.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "NODE_HIERARCHY_MAPPING")
@IdClass(NodeHierarchyMappingEntity.Key.class)
public class NodeHierarchyMappingEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HIERARCHY_ID")
    protected HierarchyEntity hierarchy;

    @Id
    @Column(name = "NODE_ID")
    protected Long nodeId;


    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected HierarchyEntity hierarchy;

        protected Long nodeId;

    }

}
