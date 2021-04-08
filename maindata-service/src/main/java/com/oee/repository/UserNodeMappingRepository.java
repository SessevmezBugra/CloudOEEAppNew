package com.oee.repository;

import com.oee.entity.UserNodeMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeMappingRepository extends JpaRepository<UserNodeMappingEntity, UserNodeMappingEntity.Key> {
}
