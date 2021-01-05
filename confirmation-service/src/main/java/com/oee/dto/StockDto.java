package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StockDto {

    private Long stockId;
    private Long materialId;
    private String materialDesc;
    private String materialNumber;
    private Double quantity;
    private Long warehouseId;
    private String warehouseName;

}
