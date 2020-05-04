package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.ProdRunData;

public interface ProdRunDataRepository extends JpaRepository<ProdRunData, Long>{
	
	List<ProdRunData> findByProdRunHdrRunId(Long id);
}
