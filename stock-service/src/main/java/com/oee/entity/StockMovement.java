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
@Table(name="STOCK_MOVEMENT")
public class StockMovement {
	
	@Id
	@Column(name="STOCK_MOV_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stockMovId;
	
	@Column(name="QUANTITY")
	private Double quantity;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="STOCK_ID")
	private StockInfo stock;
	
	public StockMovement() {
		// TODO Auto-generated constructor stub
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
	
	
}
