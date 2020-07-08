package com.oee.service;

import com.oee.entity.Status;

import java.util.List;

public interface StatusService {

    Status create(Status status);

    Status update(Status status);

    Boolean deleteById(Long statusId);

    Status findById(Long id);

    List<Status> findAll();

}
