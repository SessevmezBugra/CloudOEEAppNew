package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.UnitOfMeasureEntity;

@Repository
public interface UOMRepository extends JpaRepository<UnitOfMeasureEntity, Integer>{

}
