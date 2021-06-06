package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="PLANT")
@Getter
@Setter
@NoArgsConstructor
public class PlantEntity implements Serializable {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
	private NodeEntity node;

	@JsonBackReference
	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name="SOURCE_PLANT_ID")
	private PlantEntity sourcePlant;
	
	@Column(name="PLANT_NAME")
	private String name;

	@JsonManagedReference
	@OneToMany(mappedBy="sourcePlant", fetch = FetchType.LAZY)
	private List<PlantEntity> referencePlants;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<UserPlantMappingEntity> users;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<MaterialEntity> materials;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<WarehouseEntity> warehouses;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<ReasonCodeEntity> reasonCodes;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<QualityTypeEntity> qualityTypes;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<WorkCenterEntity> workCenters;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<MachineEntity> machines;
	
}
