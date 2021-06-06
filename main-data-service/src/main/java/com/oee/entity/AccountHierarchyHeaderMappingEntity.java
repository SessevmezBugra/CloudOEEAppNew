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
@Table(name = "USER_HIERARCHY_MAPPING")
@IdClass(AccountHierarchyHeaderMappingEntity.Key.class)
public class AccountHierarchyHeaderMappingEntity {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HIERARCHY_HEADER_ID")
    private HierarchyHeaderEntity hierarchy;

    @Id
    @JoinColumn(name = "USERNAME")
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected HierarchyHeaderEntity hierarchy;

        protected String username;

    }
}
