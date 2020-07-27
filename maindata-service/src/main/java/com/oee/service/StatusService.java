package com.oee.service;

import com.oee.entity.OrderStatus;

import java.util.List;

public interface StatusService {

    OrderStatus create(OrderStatus orderStatus);

    OrderStatus update(OrderStatus orderStatus);

    Boolean deleteById(Long statusId);

    OrderStatus findById(Long id);

    List<OrderStatus> findAll();

}
