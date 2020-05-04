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
//test
@Entity
@Table(name="AUTHORITIES")
public class Authority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private Long roleId;

	@JsonBackReference
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private User user;
	
	@Column(name="ROLE")
	private String role;


	public Authority() {
		// TODO Auto-generated constructor stub
	}
	
	public Authority(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
