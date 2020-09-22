package com.oee.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class UserEntityDto {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonManagedReference
    private List<ResponsibleAreaDto> responsibleAreas;

}
