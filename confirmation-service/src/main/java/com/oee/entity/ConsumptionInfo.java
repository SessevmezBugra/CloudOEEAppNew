package com.oee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONSUMPTION_INFO")
public class ConsumptionInfo {
	
	@Id
	@Column(name="CONSUMPTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long consumptionId;
	
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@Column(name="MATERIAL_ID")
	private Long materialId;
	
	@Column(name="CONSUMPTION_TIME")
	private Date consumptionTime;
	
	@Column(name="QUANTITY")
	private Double quantity;
	
	public ConsumptionInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(Long consumptionId) {
		this.consumptionId = consumptionId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Date getConsumptionTime() {
		return consumptionTime;
	}

	public void setConsumptionTime(Date consumptionTime) {
		this.consumptionTime = consumptionTime;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
}
