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
@Table(name="USER_CAREER_INFO")
public class UserCareerInfo {
	
	@Id
	@Column(name="CAREER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long careerId;
	
	@JsonBackReference
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private User user;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	public UserCareerInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getCareerId() {
		return careerId;
	}

	public void setCareerId(Long careerId) {
		this.careerId = careerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
