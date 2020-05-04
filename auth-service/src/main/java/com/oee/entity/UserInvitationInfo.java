package com.oee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_INVITATION_INFO")
public class UserInvitationInfo {
	
	@Id
	@Column(name="INVITATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long invitationId;
	
	@Column(name="FROM_COMPANY")
	private String fromCompany;
	
	@Column(name="TO_USER")
	private String toUser;
	
	@Column(name="STATUS")
	private String status;
	
	public UserInvitationInfo() {
		// TODO Auto-generated constructor stub
	}

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
}
