package com.oee.repository;

import com.oee.entity.ReasonCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReasonCodeRepository extends JpaRepository<ReasonCodeEntity, Long> {
    List<ReasonCodeEntity> findByPlantId(Long id);
}
