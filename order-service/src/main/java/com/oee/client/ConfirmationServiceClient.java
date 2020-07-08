package com.oee.client;

import com.oee.config.ClientConfiguration;
import com.oee.dto.ProdRunHdrDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "confirmation-service", configuration = {ClientConfiguration.class})
public interface ConfirmationServiceClient {

    @RequestMapping(value = "/confirmation/prodrunhdr/start", method= RequestMethod.POST)
    public ResponseEntity<ProdRunHdrDto> start(@RequestBody ProdRunHdrDto prodRunHdrDto);

    @RequestMapping(value = "/confirmation/prodrunhdr/hold", method= RequestMethod.PUT)
    public ResponseEntity<ProdRunHdrDto> hold(@RequestBody ProdRunHdrDto prodRunHdrDto);

    @RequestMapping(value = "/confirmation/prodrunhdr/complete", method= RequestMethod.PUT)
    public ResponseEntity<ProdRunHdrDto> complete(@RequestBody ProdRunHdrDto prodRunHdrDto);

}
