package com.oee.integration;

import com.oee.common.ServicesTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UOMIntegrationTest extends ServicesTestSupport {

    @Test
    public void test32() {
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
//        ResponseEntity response = this.testRestTemplate.getForEntity("/auth/user?username=bses", String.class);
        final ResponseEntity response = testRestTemplate.exchange("/main-data/uom", HttpMethod.GET,
                httpEntity, String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
