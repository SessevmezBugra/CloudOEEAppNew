package com.oee.entity;

import com.oee.enums.HierarchyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HIERARCHY_HEADER")
public class HierarchyHeaderEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")
    private HierarchyEntity child;

    @Column(name="NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="HIERARCHY_TYPE")
    private HierarchyType type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AccountHierarchyHeaderMappingEntity user;
}