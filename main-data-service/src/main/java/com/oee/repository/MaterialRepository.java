package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.MaterialEntity;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long>{
	

}
