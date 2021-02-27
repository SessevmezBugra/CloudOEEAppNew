package com.oee.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PLANT")
@Getter
@Setter
@NoArgsConstructor
public class PlantEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
	private NodeEntity node;
	
	@Column(name="PLANT_NAME")
	private String name;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<MaterialEntity> materials;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<WarehouseEntity> warehouses;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<ReasonCodeEntity> reasonCodes;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="plant", fetch = FetchType.LAZY)
	private List<QualityTypeEntity> qualityTypes;
	
}