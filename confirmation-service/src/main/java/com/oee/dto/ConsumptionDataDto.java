package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConsumptionDataDto {

    private Long consumptionId;
    private Long stockId;
    private Long materialId;
    private String materialDesc;
    private Long warehouseId;
    private String warehouseName;
    private Date confirmationTime;
    private Double quantity;
    private String user;

}
