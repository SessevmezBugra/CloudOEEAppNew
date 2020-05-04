package com.oee.service.impl;

import org.springframework.stereotype.Service;

import com.oee.entity.StockInfo;
import com.oee.repository.StockRepository;
import com.oee.service.StockInfoService;

@Service
public class StockInfoServiceImpl implements StockInfoService{
	
	private final StockRepository stockRepository;
	
	public StockInfoServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public StockInfo create(StockInfo stockInfo) {
		return stockRepository.save(stockInfo);
	}

	@Override
	public StockInfo update(StockInfo stockInfo) {
		return stockRepository.save(stockInfo);
	}

	@Override
	public Boolean delete(Long id) {
		stockRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public StockInfo getById(Long id) {
		return stockRepository.findById(id).get();
	}

}
