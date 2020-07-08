package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="CONSUMPTION_INFO")
public class ConsumptionInfo {
	
	@Id
	@Column(name="CONSUMPTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long consumptionId;

	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="RUN_ID")
	private ProdRunHdr prodRunHdr;
	
	@Column(name="MATERIAL_ID")
	private Long materialId;
	
	@Column(name="CONSUMPTION_TIME")
	private Date consumptionTime;
	
	@Column(name="QUANTITY")
	private Double quantity;

	@Column(name="USER")
	private String user;
	
	public ConsumptionInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(Long consumptionId) {
		this.consumptionId = consumptionId;
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


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ProdRunHdr getProdRunHdr() {
		return prodRunHdr;
	}

	public void setProdRunHdr(ProdRunHdr prodRunHdr) {
		this.prodRunHdr = prodRunHdr;
	}
}
