package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{


}