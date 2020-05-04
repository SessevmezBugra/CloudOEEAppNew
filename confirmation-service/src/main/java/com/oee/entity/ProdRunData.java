package com.oee.entity;

import java.util.Date;

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
@Table(name="PROD_RUN_DATA")
public class ProdRunData {
	
	@Id
	@Column(name="ENTRY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long entryId;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="RUN_ID")
	private ProdRunHdr prodRunHdr;
	
	@Column(name="QUANTITY")
	private Double quantity;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="END_TIME")
	private Date endTime;
	
	
	
	public ProdRunData() {
		// TODO Auto-generated constructor stub
	}

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ProdRunHdr getProdRunHdr() {
		return prodRunHdr;
	}

	public void setProdRunHdr(ProdRunHdr prodRunHdr) {
		this.prodRunHdr = prodRunHdr;
	}
	
	
}
