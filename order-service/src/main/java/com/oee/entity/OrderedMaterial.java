package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="ORDERED_MATERIAL")
public class OrderedMaterial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private OrderInfo order;
	
	@Column(name="MATERIAL_DESC")
	private String materialDesc;

	@Column(name="MATERIAL_NUMBER")
	private String materialNumber;

	@Column(name="PLANNED_PROD_QUANTITY")
	private Double plannedProdQuantity;
	
	@Column(name="ACTUAL_PROD_QUANTITY")
	private Double actualProdQuantity = 0.0;
	
	public OrderedMaterial() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public Double getPlannedProdQuantity() {
		return plannedProdQuantity;
	}

	public void setPlannedProdQuantity(Double plannedProdQuantity) {
		this.plannedProdQuantity = plannedProdQuantity;
	}

	public Double getActualProdQuantity() {
		return actualProdQuantity;
	}

	public void setActualProdQuantity(Double actualProdQuantity) {
		this.actualProdQuantity = actualProdQuantity;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}


	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
}
