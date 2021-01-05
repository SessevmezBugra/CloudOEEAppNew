package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SCRAP")
@Getter
@Setter
@NoArgsConstructor
public class Scrap {

    @Id
    @Column(name="SCRAP_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long scrapId;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ENTRY_ID", nullable = false)
    private ProdRunData prodRunData;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, mappedBy="scrap")
    private List<ScrapDetail> scrapDetails;

    @Column(name="SCRAP_DESC")
    private String scrapDesc;

}
