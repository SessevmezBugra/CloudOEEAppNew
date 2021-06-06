package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    private NodeEntity node;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="account", fetch = FetchType.LAZY)
    private List<UserAccountMappingEntity> users;

}
