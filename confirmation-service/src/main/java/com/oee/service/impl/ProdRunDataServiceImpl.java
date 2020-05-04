package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.ProdRunData;
import com.oee.repository.ProdRunDataRepository;
import com.oee.service.ProdRunDataService;


@Service
public class ProdRunDataServiceImpl implements ProdRunDataService{
	
	private final ProdRunDataRepository prodRunDataRepository;
	
	public ProdRunDataServiceImpl(ProdRunDataRepository prodRunDataRepository) {
		this.prodRunDataRepository = prodRunDataRepository;
	}

	@Override
	public ProdRunData create(ProdRunData prodRunData) {
		return prodRunDataRepository.save(prodRunData);
	}

	@Override
	public ProdRunData update(ProdRunData prodRunData) {
		return prodRunDataRepository.save(prodRunData);
	}

	@Override
	public Boolean delete(Long id) {
		prodRunDataRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ProdRunData getById(Long entryId) {
		return prodRunDataRepository.findById(entryId).get();
	}

	@Override
	public List<ProdRunData> getByRunId(Long runId) {
		return prodRunDataRepository.findByProdRunHdrRunId(runId);
	}

}
