package com.oee.service;

import com.oee.entity.ReasonCode;

import java.util.List;

public interface ReasonCodeService {

    ReasonCode create(ReasonCode reasonCode);

    ReasonCode update(ReasonCode reasonCode);

    Boolean deleteById(Long id);

    ReasonCode findById(Long id);

    List<ReasonCode> findAll();

    List<ReasonCode> findByPlantId(Long id);
}
