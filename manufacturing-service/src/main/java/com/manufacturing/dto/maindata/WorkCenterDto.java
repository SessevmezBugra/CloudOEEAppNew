package com.manufacturing.dto.maindata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkCenterDto {
    private Long id;
    private NodeDto node;
    private PlantDto plant;
    private String name;
    private String desc;
    private List<UserWorkCenterMappingDto> users;

}
