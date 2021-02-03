package com.oee.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "NODE_USER_MAPPING")
@IdClass(NodeUserMappingEntity.Key.class)
public class NodeUserMappingEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    protected NodeEntity node;

    @Id
    @Column(name = "USERNAME")
    protected String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected NodeEntity node;

        protected String username;

    }
}
