package com.manufacturing.dto;

public class StockInfoDto {

private Long stockId;
	
	private Long materialId;
	
	private Double quantity;
	
	private Integer warehouseId;
	
	public StockInfoDto() {
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

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
}
