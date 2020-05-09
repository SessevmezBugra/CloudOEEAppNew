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
	private Integer warehouseId;
	
	@Column(name="CLIENT_NAME")
	private String clientName;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="PLANT_ID")
	private PlantInfo plant;
	
	public WarehouseInfo() {
		
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public PlantInfo getPlant() {
		return plant;
	}

	public void setPlant(PlantInfo plant) {
		this.plant = plant;
	}
	
	
}
