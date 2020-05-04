package com.oee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERED_MATERIAL")
public class OrderedMaterial {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(optional=true)
	@JoinColumn(name="ORDER_ID")
	private OrderInfo order;
	
	@Column(name="MATERIAL_DESC")
	private String materialDesc;
	
	@Column(name="PLANNED_PROD_QUANTITY")
	private Double plannedProdQuantity;
	
	@Column(name="ACTUAL_PROD_QUANTITY")
	private Double actualProdQuantity;
	
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
	
	
}
