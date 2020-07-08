package com.oee.service;

import java.util.List;

import com.oee.entity.ConsumptionInfo;

public interface ConsumptionInfoService {

	ConsumptionInfo create(ConsumptionInfo consumptionInfo);
	
	ConsumptionInfo update(ConsumptionInfo consumptionInfo);
	
	Boolean delete(Long id);
	
	ConsumptionInfo getById(Long consumptionId);
	
	List<ConsumptionInfo> getByRunId(Long runId);
	
}
