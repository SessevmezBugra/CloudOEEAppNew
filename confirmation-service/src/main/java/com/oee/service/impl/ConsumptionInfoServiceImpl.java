package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.ConsumptionInfo;
import com.oee.repository.ConsumptionInfoRepository;
import com.oee.service.ConsumptionInfoService;

@Service
public class ConsumptionInfoServiceImpl implements ConsumptionInfoService{
	
	private final ConsumptionInfoRepository consumptionInfoRepository;
	
	public ConsumptionInfoServiceImpl(ConsumptionInfoRepository consumptionInfoRepository) {
		this.consumptionInfoRepository = consumptionInfoRepository;
	}
	
	@Override
	public ConsumptionInfo create(ConsumptionInfo consumptionInfo) {
		return consumptionInfoRepository.save(consumptionInfo);
	}

	@Override
	public ConsumptionInfo update(ConsumptionInfo consumptionInfo) {
		return consumptionInfoRepository.save(consumptionInfo);
	}

	@Override
	public Boolean delete(Long id) {
		consumptionInfoRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ConsumptionInfo getById(Long consumptionId) {
		return consumptionInfoRepository.findById(consumptionId).get();
	}

	@Override
	public List<ConsumptionInfo> getByOrderId(Long orderId) {
		return consumptionInfoRepository.findByOrderId(orderId);
	}

}
