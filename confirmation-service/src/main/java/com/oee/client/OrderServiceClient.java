package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.OrderDto;
import com.oee.dto.OrderedMaterialDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order-service", configuration = {ClientConfiguration.class})
public interface OrderServiceClient {

    @RequestMapping(value = "/order/ordered-material/add-production/{orderId}/{quantity}", method= RequestMethod.PUT)
    public ResponseEntity<OrderedMaterialDto> addProductionToActualProd(@PathVariable(value="orderId", required=true) Long orderId, @PathVariable(value="quantity", required=true) Double quantity);

    @RequestMapping(value = "/order/ordered-material/order/{id}", method= RequestMethod.GET)
    public ResponseEntity<OrderedMaterialDto> getOrderedMaterialByOrderId(@PathVariable(value="id", required=true) Long orderId);

    @RequestMapping(value = "/order/order-info/{id}", method= RequestMethod.GET)
    public ResponseEntity<OrderDto> getOrderInfoByOrderId(@PathVariable(value="id", required=true) Long orderId);

}