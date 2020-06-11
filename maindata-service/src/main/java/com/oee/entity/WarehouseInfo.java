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
@Table(name="WAREHOUSE_INFO")
public class WarehouseInfo {

	@Id
	@Column(name="WAREHOUSE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long warehouseId;
	
	@Column(name="WAREHOUSE_NAME")
	private String warehouseName;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="PLANT_ID")
	private PlantInfo plant;
	
	public WarehouseInfo() {
		
	}

	public PlantInfo getPlant() {
		return plant;
	}

	public void setPlant(PlantInfo plant) {
		this.plant = plant;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
}
