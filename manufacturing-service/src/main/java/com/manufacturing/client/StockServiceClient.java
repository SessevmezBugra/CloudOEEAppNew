package com.manufacturing.client;

import com.manufacturing.config.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "stock-service", configuration = {ClientConfiguration.class})
public interface StockServiceClient {

    @RequestMapping(value = "/stock/stockinfo/warehouse/warehouse-ids", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteStockByWarehouseIds(@RequestBody List<Long> warehouseIds);

}
