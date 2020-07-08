package com.oee.service.impl;

import com.oee.entity.Status;
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
    public Status create(Status status) {
        return repository.save(status);
    }

    @Override
    public Status update(Status status) {
        return repository.save(status);
    }

    @Override
    public Boolean deleteById(Long statusId) {
        repository.deleteById(statusId);
        return Boolean.TRUE;
    }

    @Override
    public Status findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Status> findAll() {
        return repository.findAll();
    }
}
