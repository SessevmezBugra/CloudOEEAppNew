package com.oee.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ResponsibleAreaDto {

    private Long id;
    private Long areaId;
    private UserEntityDto userEntity;

}
