package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.OrderedMaterialDto;
import com.oee.entity.ProdRunData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order-service", configuration = {ClientConfiguration.class})
public interface OrderServiceClient {

    @RequestMapping(value = "/order/ordered-material/add-production/{orderId}/{quantity}", method= RequestMethod.PUT)
    public ResponseEntity<OrderedMaterialDto> addProductionToActualProd(@PathVariable(value="orderId", required=true) Long orderId, @PathVariable(value="quantity", required=true) Double quantity);

}