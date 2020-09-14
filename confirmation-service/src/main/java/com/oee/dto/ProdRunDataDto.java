package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProdRunDataDto {

    private Long entryId;
    private Double quantity;
    private String user;
    private Long qualityId;
    private String qualityType;
    private Date confirmationTime;

}
