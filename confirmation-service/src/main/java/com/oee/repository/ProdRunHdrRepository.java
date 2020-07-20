package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.ProdRunHdr;

import javax.transaction.Transactional;

public interface ProdRunHdrRepository extends JpaRepository<ProdRunHdr, Long>{
	
	List<ProdRunHdr> findByOrderId(Long orderId);

	ProdRunHdr findTopByOrderIdOrderByRunIdDesc(Long orderId);

	@Transactional
    void deleteByOrderIdIn(List<Long> orderIds);
}
