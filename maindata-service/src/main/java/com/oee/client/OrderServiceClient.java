package com.oee.client;

import com.oee.config.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "order-service", configuration = {ClientConfiguration.class})
public interface OrderServiceClient {
    @RequestMapping(value = "/order/orderinfo/plant/plant-ids", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteOrderByPlantIds(@RequestBody List<Long> plantIds);
}
