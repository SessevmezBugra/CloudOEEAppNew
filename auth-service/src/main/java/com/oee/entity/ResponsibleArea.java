package com.oee.entity;

import javax.persistence.CascadeType;
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
@Table(name="RESPONSIBLE_AREA")
public class ResponsibleArea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESPONSIBLE_ID")
	private Long responsibleId;
	
	@JsonBackReference
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private User user;
	
	@Column(name="AREA_ID")
	private Long areaId;
	
	@Column(name="AREA_TYPE")
	private String areaType;
	
	public ResponsibleArea() {
		// TODO Auto-generated constructor stub
	}

	public Long getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(Long responsibleId) {
		this.responsibleId = responsibleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	
}
