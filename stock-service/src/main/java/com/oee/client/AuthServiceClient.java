package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.ResponsibleAreaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "auth-service", configuration = {ClientConfiguration.class})
public interface AuthServiceClient {

        @RequestMapping(value = "/auth/responsible-area", method= RequestMethod.GET)
        public ResponseEntity<List<ResponsibleAreaDto>> getResponsibleArea();

        @RequestMapping(value = "/auth/responsible-area", method= RequestMethod.POST)
        public ResponseEntity<ResponsibleAreaDto> addCompanyToResponsibleArea(@RequestBody ResponsibleAreaDto responsibleAreaDto);

        @RequestMapping(value = "/auth/keycloak/company-owner", method= RequestMethod.POST)
        public ResponseEntity<Boolean> addCompanyOwnerRole();
}
