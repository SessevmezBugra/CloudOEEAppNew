package com.oee.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oee.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ORDER_INFO")
@NoArgsConstructor
@Getter
@Setter
public class OrderInfo {
	
	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(name="PLANT_ID")
	private Long plantId;

	@Column(name="WORK_CENTER_ID")
	private Long workCenterId;
	
	@Column(name="ORDER_NO")
	private String orderNo;

	@Column(name="CUSTOMER")
	private String customer;
	
	@Column(name="ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Column(name="PLANNED_START_DATE")
	private Date plannedStartDate;
	
	@Column(name="ACTUAL_START_DATE")
	private Date actualStartDate;
	
	@Column(name="PLANNED_END_DATE")
	private Date plannedEndDate;
	
	@Column(name="ACTUAL_END_DATE")
	private Date actualEndDate;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private OrderedMaterial orderedMaterial;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order")
	private List<ConsumptionStock> consumptionStocks;

	@Column(name="CREATED_USER")
	private String createdUser;

}
