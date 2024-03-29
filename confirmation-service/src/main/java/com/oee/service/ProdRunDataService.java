package com.oee.service;

import java.util.List;

import com.oee.dto.ProdRunDataDto;
import com.oee.entity.ProdRunData;

public interface ProdRunDataService {
	
	ProdRunData create(ProdRunData prodRunData);
	
	ProdRunData update(ProdRunData prodRunData);
	
	Boolean delete(Long id);
	
	ProdRunData getById(Long entryId);
	
	List<ProdRunDataDto> getByRunId(Long runId);

	List<ProdRunData> createAll(Long orderId, List<ProdRunData> prodRunDatas);
}
