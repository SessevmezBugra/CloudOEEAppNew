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
@Table(name = "ACCOUNT_HIERARCHY_MAPPING")
@IdClass(AccountHierarchyMapping.Key.class)
public class AccountHierarchyMapping {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity accountEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HIERARCHY_ID")
    private HierarchyEntity hierarchyEntity;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected AccountEntity accountEntity;

        protected HierarchyEntity hierarchyEntity;

    }
}
