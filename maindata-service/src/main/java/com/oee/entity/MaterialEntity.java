package com.oee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="MATERIAL")
@Getter
@Setter
@NoArgsConstructor
public class MaterialEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="MATERIAL_NUMBER")
	private String number;

	@Column(name="MATERIAL_NAME")
	private String name;

	@Column(name="MATERIAL_DESC")
	private String desc;

	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name="PLANT_ID", referencedColumnName = "ID")
	private PlantEntity plant;
	
}
