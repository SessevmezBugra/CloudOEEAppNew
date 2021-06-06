package com.manufacturing.dto.maindata;

import com.manufacturing.enums.DataElementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReasonCodeDto {
    private Long id;
    private PlantDto plant;
    private DataElementType type;
    private String code;
    private String desc;




}
