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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="PROD_RUN_HDR")
public class ProdRunHdr {
	
	@Id
	@Column(name="RUN_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long runId;
	
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="END_TIME")
	private Date endTime;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="prodRunHdr")
	private List<ProdRunData> prodRunDatas;
	
	public ProdRunHdr() {
		// TODO Auto-generated constructor stub
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public List<ProdRunData> getProdRunDatas() {
		return prodRunDatas;
	}

	public void setProdRunDatas(List<ProdRunData> prodRunDatas) {
		this.prodRunDatas = prodRunDatas;
	}
	
	
}
