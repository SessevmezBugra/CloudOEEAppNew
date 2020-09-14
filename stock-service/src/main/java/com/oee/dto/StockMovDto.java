package com.oee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockMovDto {

    private Long stockMovId;
    private StockDto stock;
    private Double quantity;
    private String username;
    private Boolean isPositive;

}
