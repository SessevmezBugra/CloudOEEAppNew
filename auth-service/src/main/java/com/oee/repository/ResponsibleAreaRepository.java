package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.ResponsibleArea;

@Repository
public interface ResponsibleAreaRepository extends JpaRepository<ResponsibleArea, Long>{
	
	List<ResponsibleArea> findByUserId(String userId);
	
}
