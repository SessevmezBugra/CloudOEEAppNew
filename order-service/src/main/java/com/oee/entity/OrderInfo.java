package com.oee.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oee.enums.Status;

@Entity
@Table(name="ORDER_INFO")
public class OrderInfo {
	
	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(name="PLANT_ID")
	private Long plantId;
	
	@Column(name="ORDER_NO")
	private String orderNo;

	@Column(name="CUSTOMER")
	private String customer;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name="PLANNED_START_DATE")
	private Date plannedStartDate;
	
	@Column(name="ACTUAL_START_DATE")
	private Date actualStartDate;
	
	@Column(name="PLANNED_END_DATE")
	private Date plannedEndDate;
	
	@Column(name="ACTUAL_END_DATE")
	private Date actualEndDate;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private OrderedMaterial orderedMaterial;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order")
	private List<ConsumptionStock> consumptionStocks;

	@Column(name="CREATED_USER")
	private String createdUser;

	public OrderInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public List<ConsumptionStock> getConsumptionStocks() {
		return consumptionStocks;
	}

	public void setConsumptionStocks(List<ConsumptionStock> consumptionStocks) {
		this.consumptionStocks = consumptionStocks;
	}


	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Long getPlantId() {
		return plantId;
	}

	public void setPlantId(Long plantId) {
		this.plantId = plantId;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
