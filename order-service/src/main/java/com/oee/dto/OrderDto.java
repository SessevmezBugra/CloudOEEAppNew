package com.oee.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long orderId;
    private Long plantId;
    private String plantName;
    private String orderNo;
    private String customer;
    private String status;
    private Date plannedStartDate;
    private Date actualStartDate;
    private Date plannedEndDate;
    private Date actualEndDate;
    private String materialDesc;
    private String materialNumber;

}
