package com.oee.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsibleAreaDto {


    private Long id;

    private Long areaId;

    private AreaType areaType;

    private UserRole userRole;

    @JsonBackReference
    private UserEntityDto userEntity;

    private String areaName;

}