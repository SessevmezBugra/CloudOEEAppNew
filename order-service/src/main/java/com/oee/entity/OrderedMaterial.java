package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ORDERED_MATERIAL")
@Getter
@Setter
@NoArgsConstructor
public class OrderedMaterial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private OrderInfo order;

	@Column(name="MATERIAL_ID")
	private Long materialId;
	
	@Column(name="MATERIAL_DESC")
	private String materialDesc;

	@Column(name="MATERIAL_NUMBER")
	private String materialNumber;

	@Column(name="WAREHOUSE_ID")
	private Long warehouseId;

	@Column(name="PLANNED_PROD_QUANTITY")
	private Double plannedProdQuantity = 0.0;
	
	@Column(name="ACTUAL_PROD_QUANTITY")
	private Double actualProdQuantity = 0.0;

	@Column(name="IS_STOCK_PROD")
	private Boolean isStockProd;

}
