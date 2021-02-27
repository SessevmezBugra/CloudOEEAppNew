package com.oee.repository;

import com.oee.entity.NodeUserMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeUserMappingRepository extends JpaRepository<NodeUserMappingEntity, NodeUserMappingEntity.Key> {
}
