package com.oee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "STOCK_MOVEMENT")
@Setter
@Getter
@NoArgsConstructor
public class StockMovement {

    @Id
    @Column(name = "STOCK_MOV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockMovId;

    @JsonBackReference
    @ManyToOne(optional = true)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @Column(name = "QUANTITY")
    private Double quantity;

    @Column(name = "USERNAME")
    private String username;

	@Column(name = "IS_POSITIVE")
    private Boolean isPositive;

}
