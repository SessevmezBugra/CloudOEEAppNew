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
@Table(name="PLANT_INFO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlantInfo {
	
	@Id
	@Column(name="PLANT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer plantId;
	
	@Column(name="PLANT_NAME")
	private String plantName;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant")
	private List<MaterialInfo> materials;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="CLIENT_ID", referencedColumnName="CLIENT_ID")
	private ClientInfo client;
	
	public PlantInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public ClientInfo getClient() {
		return client;
	}

	public void setClient(ClientInfo client) {
		this.client = client;
	}

	public List<MaterialInfo> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialInfo> materials) {
		this.materials = materials;
	}

	public Integer getPlantId() {
		return plantId;
	}

	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}
	
	
}
