package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.ProdRunHdr;
import com.oee.repository.ProdRunHdrRepository;
import com.oee.service.ProdRunHdrService;

@Service
public class ProdRunHdrServiceImpl implements ProdRunHdrService{

	private final ProdRunHdrRepository prodRunHdrRepository;
	
	public ProdRunHdrServiceImpl(ProdRunHdrRepository prodRunHdrRepository) {
		this.prodRunHdrRepository = prodRunHdrRepository;
	}
	
	@Override
	public ProdRunHdr create(ProdRunHdr prodRunHdr) {
		return prodRunHdrRepository.save(prodRunHdr);
	}

	@Override
	public ProdRunHdr update(ProdRunHdr prodRunHdr) {
		return prodRunHdrRepository.save(prodRunHdr);
	}

	@Override
	public Boolean delete(Long id) {
		prodRunHdrRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ProdRunHdr getById(Long runId) {
		return prodRunHdrRepository.findById(runId).get();
	}

	@Override
	public List<ProdRunHdr> getByOrderId(Long orderId) {
		return prodRunHdrRepository.findByOrderId(orderId);
	}

}
