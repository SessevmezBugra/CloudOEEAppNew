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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PLANT_INFO")
@Getter
@Setter
@NoArgsConstructor
public class PlantInfo {
	
	@Id
	@Column(name="PLANT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long plantId;
	
	@Column(name="PLANT_NAME")
	private String plantName;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant")
	private List<MaterialInfo> materials;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant")
	private List<WarehouseInfo> warehouses;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant")
	private List<ReasonCode> reasonCodes;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant")
	private List<QualityType> qualityTypes;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant")
	private List<Machine> machines;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="CLIENT_ID", referencedColumnName="CLIENT_ID")
	private ClientInfo client;

	
}
