package com.manufacturing.dto.maindata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlantDto implements Serializable {
	private Long id;
	private NodeDto node;
	private PlantDto sourcePlant;
	private String name;
	private List<PlantDto> referencePlants;
	private List<UserPlantMappingDto> users;
	private List<MaterialDto> materials;
	private List<WarehouseDto> warehouses;
	private List<ReasonCodeDto> reasonCodes;
	private List<QualityTypeDto> qualityTypes;
	private List<WorkCenterDto> workCenters;
	private List<MachineDto> machines;
	
}
