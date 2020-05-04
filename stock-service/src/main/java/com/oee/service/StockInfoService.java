package com.oee.service;

import com.oee.entity.StockInfo;

public interface StockInfoService {
	
	StockInfo create(StockInfo stockInfo);
	
	StockInfo update(StockInfo stockInfo);
	
	Boolean delete(Long id);
	
	StockInfo getById(Long id);
	
}
