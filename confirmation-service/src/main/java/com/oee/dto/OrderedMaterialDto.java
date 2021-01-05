package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
public class OrderedMaterialDto {

    private Long id;
    private String materialDesc;
    private String materialNumber;
    private Double plannedProdQuantity;
    private Double actualProdQuantity = 0.0;
    private Boolean isStockProd;
    private Long materialId;
    private Long warehouseId;

}
