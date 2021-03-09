package com.oee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="STOCK_INFO")
@Getter
@Setter
@NoArgsConstructor
public class Stock {
	
	@Id
	@Column(name="STOCK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stockId;
	
	@Column(name="MATERIAL_ID")
	private Long materialId;
	
	@Column(name="QUANTITY")
	private Double quantity;
	
	@Column(name="WAREHOUSE_ID")
	private Long warehouseId;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="stock")
	private List<StockMovement> stockMovements;

}
