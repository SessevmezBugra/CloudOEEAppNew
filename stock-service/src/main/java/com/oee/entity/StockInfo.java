package com.oee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="STOCK_INFO")
public class StockInfo {
	
	@Id
	@Column(name="STOCK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stockId;
	
	@Column(name="MATERIAL_ID")
	private Long materialId;
	
	@Column(name="QUANTITY")
	private Double quantity;
	
	@Column(name="WAREHOUSE_ID")
	private Long warehouseId;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="stock")
	private List<StockMovement> stockMovements;
	
	public StockInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public List<StockMovement> getStockMovements() {
		return stockMovements;
	}

	public void setStockMovements(List<StockMovement> stockMovements) {
		this.stockMovements = stockMovements;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
}
