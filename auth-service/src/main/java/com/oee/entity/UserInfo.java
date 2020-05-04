package com.oee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="USER_INFO")
public class UserInfo {
	
	@Id
	@Column(name="USER_INFO_ID")
	private Long userInfoId;
	
	@JsonBackReference
	@OneToOne(optional=true)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
