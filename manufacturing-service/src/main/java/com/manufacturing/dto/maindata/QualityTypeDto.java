package com.manufacturing.dto.maindata;


import com.manufacturing.enums.DataElementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QualityTypeDto {

    private Long id;
    private PlantDto plant;
    private String name;
    private DataElementType type;


}
