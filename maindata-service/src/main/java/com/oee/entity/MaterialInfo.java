package com.oee.entity;

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
@Table(name="MATERIAL_INFO")
public class MaterialInfo {
	
	@Id
	@Column(name="MATERIAL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long materialId;
	
	@Column(name="MATERIAL_NUMBER")
	private String materialNumber;
	
	@Column(name="MATERIAL_DESC")
	private String materialDesc;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="PLANT_ID")
	private PlantInfo plant;
	
	public MaterialInfo() {
		// TODO Auto-generated constructor stub
	}


	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public PlantInfo getPlant() {
		return plant;
	}

	public void setPlant(PlantInfo plant) {
		this.plant = plant;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	
	
}
