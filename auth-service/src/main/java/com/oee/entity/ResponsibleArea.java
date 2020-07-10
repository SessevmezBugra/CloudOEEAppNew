package com.oee.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oee.enums.AreaType;

@Entity
@Table(name="RESPONSIBLE_AREA")
public class ResponsibleArea {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="AREA_ID")
	private Long areaId;

//	@Column(name="USER_ID")
//	private String userId;

	@Enumerated(EnumType.STRING)
	@Column(name="AREA_TYPE")
	private AreaType areaType;

	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="USER_ID", referencedColumnName = "ID")
	private UserEntity userEntity;
	
	public ResponsibleArea() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public AreaType getAreaType() {
		return areaType;
	}

	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
