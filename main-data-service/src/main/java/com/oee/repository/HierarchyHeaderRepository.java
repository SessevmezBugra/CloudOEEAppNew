package com.oee.repository;

import com.oee.entity.HierarchyHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierarchyHeaderRepository extends JpaRepository<HierarchyHeaderEntity, Long> {
}
