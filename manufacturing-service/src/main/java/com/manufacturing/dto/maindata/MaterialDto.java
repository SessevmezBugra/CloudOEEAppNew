package com.manufacturing.dto.maindata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaterialDto {

	private Long id;
	private String number;
	private String name;
	private String desc;
	private PlantDto plant;
	
}
