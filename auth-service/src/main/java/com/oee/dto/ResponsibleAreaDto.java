package com.oee.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oee.enums.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsibleAreaDto {


    private Long id;

    private Long areaId;

    private UserGroup userGroup;

    @JsonBackReference
    private UserEntityDto userEntity;

    private String areaName;

}