package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.UnitOfMeasures;

public interface UOMRepository extends JpaRepository<UnitOfMeasures, Integer>{

}
