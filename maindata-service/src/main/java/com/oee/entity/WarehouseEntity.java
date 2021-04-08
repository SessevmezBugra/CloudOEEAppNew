package com.oee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="WAREHOUSE")
@Getter
@Setter
@NoArgsConstructor
public class WarehouseEntity {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name="PLANT_ID")
	private PlantEntity plant;
	
	@Column(name="WAREHOUSE_NAME")
	private String name;
	
}
