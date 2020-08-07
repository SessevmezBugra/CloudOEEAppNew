package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderedMaterialDto {

    private Long id;
    private String materialDesc;
    private String materialNumber;
    private Double plannedProdQuantity;
    private Double actualProdQuantity = 0.0;
}
