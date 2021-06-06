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

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")
    private HierarchyEntity child;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HierarchyHeaderEntity header;

    @Column(name="NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="NODE_TYPE")
    private NodeType type;

    @OneToMany(mappedBy="hierarchy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NodeEntity> nodes;

}