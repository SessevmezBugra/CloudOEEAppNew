package com.oee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UNIT_OF_MEASURES")
public class UnitOfMeasures {
	
	@Id
	@Column(name="UOM_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uomId;
	
	@Column(name="UOM_CODE")
	private String	uomCode;
	
	@Column(name="UOM_DESC")
	private String	uomDesc;
	
	public UnitOfMeasures() {
		// TODO Auto-generated constructor stub
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getUomDesc() {
		return uomDesc;
	}

	public void setUomDesc(String uomDesc) {
		this.uomDesc = uomDesc;
	}

	public Integer getUomId() {
		return uomId;
	}

	public void setUomId(Integer uomId) {
		this.uomId = uomId;
	}
	
	
}
