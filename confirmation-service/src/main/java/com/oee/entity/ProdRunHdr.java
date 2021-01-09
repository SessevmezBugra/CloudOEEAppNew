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
@Table(name="PROD_RUN_HDR")
@Getter
@Setter
@NoArgsConstructor
public class ProdRunHdr {
	
	@Id
	@Column(name="RUN_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long runId;
	
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="END_TIME")
	private Date endTime;

	@Column(name="ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="prodRunHdr")
	private List<ProdRunData> prodRunDatas;

	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="prodRunHdr")
	private List<ConsumptionInfo> consumptionInfos;

	@Column(name="STARTED_USER")
	private String startedUser;

	@Column(name="ENDING_USER")
	private String endingUser;

}
