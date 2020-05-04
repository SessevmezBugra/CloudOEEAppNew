package com.oee.entity;

import java.util.ArrayList;
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
@Table(name="COMPANY_INFO")
public class CompanyInfo {
	
	@Id
	@Column(name="COMPANY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer companyId;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="USERNAME")
	private String username;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="company")
	private List<ClientInfo> clients;
	
	public CompanyInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	public List<ClientInfo> getClients() {
		return clients;
	}


	public void setClients(List<ClientInfo> clients) {
		this.clients = clients;
	}

	public void addClient(ClientInfo client) {
		if(this.clients == null) {
			this.clients = new ArrayList<ClientInfo>();
		}
		this.clients.add(client);
	}
	
	
}
