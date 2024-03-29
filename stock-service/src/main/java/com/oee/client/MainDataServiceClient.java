package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.PlantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "maindata-service", configuration = {ClientConfiguration.class})
public interface MainDataServiceClient {

    @RequestMapping(value = "/main-data/plant/{plantId}", method= RequestMethod.GET)
    public ResponseEntity<PlantDto> getPlantById(@PathVariable(value="plantId", required=true) Long plantId);

    @RequestMapping(value = "/main-data/plant/warehouse/{warehouseId}", method= RequestMethod.GET)
    public ResponseEntity<PlantDto> getPlantByWarehouseId(@PathVariable(value="warehouseId", required=true) Long warehouseId);


}
