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


@Entity
@Table(name = "STOCK_MOVEMENT")
public class StockMovement {

    @Id
    @Column(name = "STOCK_MOV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockMovId;

    @Column(name = "QUANTITY")
    private Double quantity;

    @Column(name = "USERNAME")
    private String username;

	@Column(name = "IS_POSITIVE")
    private Boolean isPositive;

    @JsonBackReference
    @ManyToOne(optional = true)
    @JoinColumn(name = "STOCK_ID")
    private StockInfo stock;

    public StockMovement() {}

    public StockInfo getStock() {
        return stock;
    }

    public void setStock(StockInfo stock) {
        this.stock = stock;
    }

    public Long getStockMovId() {
        return stockMovId;
    }

    public void setStockMovId(Long stockMovId) {
        this.stockMovId = stockMovId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public Boolean getPositive() {
		return isPositive;
	}

	public void setPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}
}
