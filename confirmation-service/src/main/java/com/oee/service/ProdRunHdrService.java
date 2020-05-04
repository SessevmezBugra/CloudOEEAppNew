package com.oee.service;

import java.util.List;

import com.oee.entity.ProdRunHdr;

public interface ProdRunHdrService {
	
	ProdRunHdr create(ProdRunHdr prodRunHdr);
	
	ProdRunHdr update(ProdRunHdr prodRunHdr);
	
	Boolean delete(Long id);
	
	ProdRunHdr getById(Long runId);
	
	List<ProdRunHdr> getByOrderId(Long orderId);
	
}
