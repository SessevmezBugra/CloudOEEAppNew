package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.MaterialDto;
import com.oee.dto.PlantDto;
import com.oee.dto.QualityTypeDto;
import com.oee.dto.WarehouseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "maindata-service", configuration = {ClientConfiguration.class})
public interface MainDataServiceClient {

    @RequestMapping(value = "/main-data/plant/{plantId}", method= RequestMethod.GET)
    public ResponseEntity<PlantDto> getPlantById(@PathVariable(value="plantId", required=true) Long plantId);

    @RequestMapping(value = "/main-data/plant/warehouse/{warehouseId}", method= RequestMethod.GET)
    public ResponseEntity<PlantDto> getPlantByWarehouseId(@PathVariable(value="warehouseId", required=true) Long warehouseId);

    @RequestMapping(value = "/main-data/plant", method= RequestMethod.GET)
    public ResponseEntity<List<PlantDto>> getPlantByLoggedUser();

    @RequestMapping(value = "/main-data/material/ids", method= RequestMethod.POST)
    public ResponseEntity<List<MaterialDto>> getMaterialsByIds(@RequestBody List<Long> ids);

    @RequestMapping(value = "/main-data/material/{id}", method= RequestMethod.GET)
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable(value="id", required=true) Long materialId);

    @RequestMapping(value = "/main-data/warehouse/ids", method= RequestMethod.POST)
    public ResponseEntity<List<WarehouseDto>> getWarehousesByIds(@RequestBody List<Long> ids);

    @RequestMapping(value = "/main-data/quality-type/plant/{id}", method= RequestMethod.GET)
    public ResponseEntity<List<QualityTypeDto>> getQualityTypesByPlantId(@PathVariable(value="id", required=true) Long plantId);

    @RequestMapping(value = "/main-data/material/plant/{id}", method= RequestMethod.GET)
    public ResponseEntity<List<MaterialDto>> getMaterialsByPlantId(@PathVariable(value="id", required=true) Long plantId);

    @RequestMapping(value = "/main-data/warehouse/plant/{id}", method= RequestMethod.GET)
    public ResponseEntity<List<WarehouseDto>> getWarehousesByPlantId(@PathVariable(value="id", required=true) Long plantId);

}