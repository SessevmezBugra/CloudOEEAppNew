package com.oee.repository;

import com.oee.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, Long> {

    List<NodeEntity> findByUsersUsername(String username);
}
