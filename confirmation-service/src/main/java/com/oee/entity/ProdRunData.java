package com.oee.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="PROD_RUN_DATA")
@Getter
@Setter
@NoArgsConstructor
public class ProdRunData {
	
	@Id
	@Column(name="ENTRY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long entryId;
	
	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="RUN_ID")
	private ProdRunHdr prodRunHdr;
	
	@Column(name="QUANTITY")
	private Double quantity;

	@Column(name="USER")
	private String user;

	@Column(name="QUALITY_ID")
	private Long qualityId;

	@Column(name="CONFIRMATION_TIME")
	private Date confirmationTime;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "prodRunData")
	private Scrap scrap;

}
