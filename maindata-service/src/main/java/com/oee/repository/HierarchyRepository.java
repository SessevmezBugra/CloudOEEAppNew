package com.oee.repository;

import com.oee.entity.HierarchyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierarchyRepository extends JpaRepository<HierarchyEntity, Long> {
}
