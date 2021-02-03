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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="PLANT_ID", referencedColumnName = "ID")
	private PlantEntity plant;
	
}
