package com.oee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oee.config.ClientConfiguration;
import com.oee.dto.StockInfoDto;

@FeignClient(name = "stock-service", configuration = {ClientConfiguration.class})
public interface StockServiceProxy {

	@RequestMapping(value = "/rest/stock/stockinfo", method=RequestMethod.POST)
	public ResponseEntity<StockInfoDto> createStockInfo(@RequestBody StockInfoDto stockInfoDto);

}