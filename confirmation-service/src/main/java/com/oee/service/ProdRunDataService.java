package com.oee.service;

import java.util.List;

import com.oee.entity.ProdRunData;

public interface ProdRunDataService {
	
	ProdRunData create(ProdRunData prodRunData);
	
	ProdRunData update(ProdRunData prodRunData);
	
	Boolean delete(Long id);
	
	ProdRunData getById(Long entryId);
	
	List<ProdRunData> getByRunId(Long runId);
}
