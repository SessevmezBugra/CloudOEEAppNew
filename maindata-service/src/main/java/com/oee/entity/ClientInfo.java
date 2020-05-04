package com.oee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="CLIENT_INFO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClientInfo {
	
	@Id
	@Column(name="CLIENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer clientId;
	
	@Column(name="CLIENT_NAME")
	private String clientName;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="client")
	private List<PlantInfo> plants;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="COMPANY_ID")
	private CompanyInfo company;
	
	

	public ClientInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public CompanyInfo getCompany() {
		return company;
	}

	public void setCompany(CompanyInfo company) {
		this.company = company;
	}

	public List<PlantInfo> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantInfo> plants) {
		this.plants = plants;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	
}
