package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SCRAP_DETAIl")
@Setter
@Getter
@NoArgsConstructor
public class ScrapDetail {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional=true)
    @JoinColumn(name="ENTRY_ID")
    private ProdRunData prodRunData;

    @Column(name="REASON_CODE")
    private String reasonCode;

    @Column(name="REASON_DESC")
    private String reasonDesc;
}
