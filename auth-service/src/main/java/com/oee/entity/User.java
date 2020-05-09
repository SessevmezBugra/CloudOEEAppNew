package com.oee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="username", length=100, unique=true)
	private String username;
	
	@Column(name="password", length=200)
	private String password;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="user")
	private List<Authority> authorities;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy="user")
	private UserInfo userInfo;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<UserCareerInfo> userCareerInfos;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<ResponsibleArea> responsibleAreas;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public void addAuthority(Authority authority) {
		if(this.authorities == null) {
			this.authorities = new ArrayList<Authority>();
		}
		this.authorities.add(authority);
	}
	public void addResponsibleArea(ResponsibleArea responsibleArea) {
		if(this.responsibleAreas == null) {
			this.responsibleAreas = new ArrayList<ResponsibleArea>();
		}
		this.responsibleAreas.add(responsibleArea);
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserCareerInfo> getUserCareerInfos() {
		return userCareerInfos;
	}

	public void setUserCareerInfos(List<UserCareerInfo> userCareerInfos) {
		this.userCareerInfos = userCareerInfos;
	}

	public List<ResponsibleArea> getResponsibleAreas() {
		return responsibleAreas;
	}

	public void setResponsibleAreas(List<ResponsibleArea> responsibleAreas) {
		this.responsibleAreas = responsibleAreas;
	}

}
