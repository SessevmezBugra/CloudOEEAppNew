package com.oee.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oee.enums.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="RESPONSIBLE_AREA")
@Getter
@Setter
@NoArgsConstructor
public class ResponsibleArea {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="AREA_ID")
	private Long areaId;

//	@Column(name="USER_ID")
//	private String userId;

//	@Enumerated(EnumType.STRING)
//	@Column(name="AREA_TYPE")
//	private AreaType areaType;

	@Enumerated(EnumType.STRING)
	@Column(name="AREA_ROLE")
	private UserGroup userGroup;

	@JsonBackReference
	@ManyToOne(optional=true)
	@JoinColumn(name="USER_ID", referencedColumnName = "ID")
	private UserEntity userEntity;

}
