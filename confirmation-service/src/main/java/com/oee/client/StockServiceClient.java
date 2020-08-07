package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "stock-service", configuration = {ClientConfiguration.class})
public interface StockServiceClient {

    @RequestMapping(value = "/stock/stock-info/extract/all", method= RequestMethod.PUT)
    public ResponseEntity<List<StockDto>> extractAllStock(@RequestBody List<StockDto> stockDtos);

}
