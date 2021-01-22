package com.oee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UNIT_OF_MEASURES")
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasures {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="UOM_CODE")
	private String	code;
	
	@Column(name="UOM_DESC")
	private String	desc;
	
}
