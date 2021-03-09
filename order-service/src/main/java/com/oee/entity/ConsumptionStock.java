package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONSUMPTION_MATERIAL")
@Getter
@Setter
@NoArgsConstructor
public class ConsumptionStock {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="ORDER_ID")
	private OrderInfo order;
	
	@Column(name="STOCK_ID")
	private Long stockId;

}
