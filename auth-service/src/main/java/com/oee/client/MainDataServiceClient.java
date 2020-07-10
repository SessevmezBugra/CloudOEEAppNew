package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.ClientDto;
import com.oee.dto.CompanyDto;
import com.oee.dto.PlantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "maindata-service", configuration = {ClientConfiguration.class})
public interface MainDataServiceClient {

    @RequestMapping(value = "/main-data/company", method= RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompaniesByLoggedUser();

    @RequestMapping(value = "/main-data/client", method= RequestMethod.GET)
    public ResponseEntity<List<ClientDto>> getClientsByLoggedUser();

    @RequestMapping(value = "/main-data/plant", method= RequestMethod.GET)
    public ResponseEntity<List<PlantDto>> getPlantsByLoggedUser();

    @RequestMapping(value = "/maindata/companyinfo", method=RequestMethod.POST)
    public ResponseEntity<CompanyDto> createCompanyInfo(@RequestBody CompanyDto companyDto);

}
