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
@Table(name = "USER_ACCOUNT_MAPPING")
@IdClass(UserAccountMappingEntity.Key.class)
public class UserAccountMappingEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity account;

    @Id
    @JoinColumn(name = "USERNAME")
    private String username;

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Key implements Serializable {

        protected AccountEntity account;

        protected String username;

    }
}
