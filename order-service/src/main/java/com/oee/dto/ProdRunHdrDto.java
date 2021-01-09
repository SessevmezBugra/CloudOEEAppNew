package com.oee.dto;

import com.oee.enums.OrderStatus;

import java.util.Date;

public class ProdRunHdrDto {

    private Long runId;

    private Long orderId;

    private Date startTime;

    private Date endTime;

    private OrderStatus orderStatus;

    private String startedUser;

    private String endingUser;

    public ProdRunHdrDto() {
    }

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStartedUser() {
        return startedUser;
    }

    public void setStartedUser(String startedUser) {
        this.startedUser = startedUser;
    }

    public String getEndingUser() {
        return endingUser;
    }

    public void setEndingUser(String endingUser) {
        this.endingUser = endingUser;
    }
}
