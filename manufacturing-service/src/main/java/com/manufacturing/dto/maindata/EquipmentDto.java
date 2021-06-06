package com.manufacturing.dto.maindata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EquipmentDto {

    private Long id;
    private MachineDto machine;
    private String name;
    private String desc;

}
