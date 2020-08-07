package com.oee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="CONSUMPTION_INFO")
@Getter
@Setter
@NoArgsConstructor
public class ConsumptionInfo {
	
	@Id
	@Column(name="CONSUMPTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long consumptionId;

	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="RUN_ID")
	private ProdRunHdr prodRunHdr;
	
	@Column(name="STOCK_ID")
	private Long stockId;
	
	@Column(name="CONFIRMATION_TIME")
	private Date confirmationTime;
	
	@Column(name="QUANTITY")
	private Double quantity;

	@Column(name="USER")
	private String user;

}
