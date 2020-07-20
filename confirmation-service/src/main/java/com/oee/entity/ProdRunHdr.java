package com.oee.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oee.enums.Status;

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

	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="prodRunHdr")
	private List<ProdRunData> prodRunDatas;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="prodRunHdr")
	private List<ConsumptionInfo> consumptionInfos;

	@Column(name="STARTED_USER")
	private String startedUser;

	@Column(name="ENDING_USER")
	private String endingUser;
	
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


	public String getStartedUser() {
		return startedUser;
	}

	public void setStartedUser(String startedUser) {
		this.startedUser = startedUser;
	}

	public String getEndingUser() {
		return endingUser;
	}

	public void setEndingUser(String endingUser) {
		this.endingUser = endingUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ConsumptionInfo> getConsumptionInfos() {
		return consumptionInfos;
	}

	public void setConsumptionInfos(List<ConsumptionInfo> consumptionInfos) {
		this.consumptionInfos = consumptionInfos;
	}
}
