package com.oee.service;

import java.util.List;

import com.oee.dto.ConsumptionStockDto;
import com.oee.entity.ConsumptionStock;

public interface ConsumptionMaterialService {
	
	ConsumptionStock create(ConsumptionStock consumptionStock);
	
	ConsumptionStock update(ConsumptionStock consumptionStock);
	
	Boolean delete(Long id);
	
	ConsumptionStock getById(Long id);
	
	List<ConsumptionStockDto> getByOrderId(Long id);

}
