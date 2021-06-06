package com.oee.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "USER_NODE_MAPPING")
@IdClass(UserNodeMappingEntity.Key.class)
public class UserNodeMappingEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private NodeEntity node;

    @Id
    @JoinColumn(name = "USERNAME")
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected NodeEntity node;

        protected String username;

    }
}
