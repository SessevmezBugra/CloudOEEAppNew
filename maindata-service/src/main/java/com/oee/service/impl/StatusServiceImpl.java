package com.oee.service.impl;

import com.oee.entity.OrderStatus;
import com.oee.repository.StatusRepository;
import com.oee.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository repository;

    public StatusServiceImpl(StatusRepository repository) {
        this.repository = repository;
    }


    @Override
    public OrderStatus create(OrderStatus orderStatus) {
        return repository.save(orderStatus);
    }

    @Override
    public OrderStatus update(OrderStatus orderStatus) {
        return repository.save(orderStatus);
    }

    @Override
    public Boolean deleteById(Long statusId) {
        repository.deleteById(statusId);
        return Boolean.TRUE;
    }

    @Override
    public OrderStatus findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<OrderStatus> findAll() {
        return repository.findAll();
    }
}
