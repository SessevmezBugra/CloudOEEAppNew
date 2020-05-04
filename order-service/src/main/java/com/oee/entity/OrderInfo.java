package com.oee.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ORDER_INFO")
public class OrderInfo {
	
	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(name="PLANT_ID")
	private Integer plantId;
	
	@Column(name="ORDER_NO")
	private String orderNo;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PLANNED_START_DATE")
	private Date plannedStartDate;
	
	@Column(name="ACTUAL_START_DATE")
	private Date actualStartDate;
	
	@Column(name="PLANNED_END_DATE")
	private Date plannedEndDate;
	
	@Column(name="ACTUAL_END_DATE")
	private Date actualEndDate;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="order")
	private OrderedMaterial orderedMaterial;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order")
	private List<ConsumptionMaterial> consumptionMaterials;
	
	public OrderInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getPlantId() {
		return plantId;
	}

	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public OrderedMaterial getOrderedMaterial() {
		return orderedMaterial;
	}

	public void setOrderedMaterial(OrderedMaterial orderedMaterial) {
		this.orderedMaterial = orderedMaterial;
	}

	public List<ConsumptionMaterial> getConsumptionMaterials() {
		return consumptionMaterials;
	}

	public void setConsumptionMaterials(List<ConsumptionMaterial> consumptionMaterials) {
		this.consumptionMaterials = consumptionMaterials;
	}
	
	
}
