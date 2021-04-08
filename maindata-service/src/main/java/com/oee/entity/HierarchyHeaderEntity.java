package com.oee.entity;

import com.oee.enums.HierarchyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")
    private HierarchyEntity child;

    @Column(name="NAME")
    private String name;

    @Column(name="HIERARCHY_TYPE")
    private HierarchyType type;

    @OneToMany(mappedBy="hierarchy", fetch = FetchType.LAZY)
    private List<UserHierarchyHeaderMappingEntity> users;
}
