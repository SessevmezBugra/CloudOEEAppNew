package com.oee.repository;

import com.oee.entity.WorkCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCenterRepository extends JpaRepository<WorkCenterEntity, Long> {
}
