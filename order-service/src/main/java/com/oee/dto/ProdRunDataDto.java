package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ProdRunDataDto {

    private Long entryId;
    private ProdRunHdrDto prodRunHdrDto;
    private Double quantity;
    private Date startTime;
    private Date endTime;
    private String user;
}
