package com.oee.repository;

import com.oee.entity.QualityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityTypeRepository extends JpaRepository<QualityType, Long> {
    List<QualityType> findByPlantPlantId(Long id);
}
