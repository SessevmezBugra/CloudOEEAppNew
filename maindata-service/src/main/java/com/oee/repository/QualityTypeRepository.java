package com.oee.repository;

import com.oee.entity.QualityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityTypeRepository extends JpaRepository<QualityTypeEntity, Long> {
    List<QualityTypeEntity> findByPlantId(Long id);
}
