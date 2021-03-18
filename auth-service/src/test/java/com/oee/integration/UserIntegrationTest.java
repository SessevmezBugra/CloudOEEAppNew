package com.oee.integration;

import com.oee.common.ServicesTestSupport;
import com.oee.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserIntegrationTest extends ServicesTestSupport {


//    @MockBean
//    private KeycloakUserDao keycloakUserDao;

//    @BeforeEach
//    public void before() {
//        testRestTemplate = testRestTemplate.withBasicAuth("testUser", "testUser");
////        testRestTemplate.getRestTemplate().getInterceptors().add(new BasicAuthenticationInterceptor("testUser", "testUser"));
//    }



    @Test
    public void findByUsername() {
//        when(keycloakUserDao.findByUsername("bses")).thenReturn(Arrays.asList(new UserRepresentation()));
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
//        ResponseEntity response = this.testRestTemplate.getForEntity("/auth/user?username=bses", String.class);
        final ResponseEntity response = testRestTemplate.exchange("/auth/user?username=bses", HttpMethod.GET,
                httpEntity, String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findByUsername_whenEmptyUsername() {
//        when(keycloakUserDao.findByUsername("")).thenReturn(Collections.emptyList());
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
        final ResponseEntity response = testRestTemplate.exchange("/auth/user?username=", HttpMethod.GET,
                httpEntity, String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findByUsername_whenUsernameNull() {
//        when(keycloakUserDao.findByUsername(null)).thenReturn(Collections.emptyList());
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
        final ResponseEntity response = testRestTemplate.exchange("/auth/user?username=null", HttpMethod.GET,
                httpEntity, String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testPost() {
//        when(keycloakUserDao.findByUsername(null)).thenReturn(Collections.emptyList());
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("testUser", "testUser"));
//        testRestTemplate.se
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth("testUser", "testUser");
// set parameter
//        HttpEntity<UserEntity> request = new HttpEntity<>(new UserEntity(), headers);
        ResponseEntity response = this.testRestTemplate.postForEntity("/auth/user", new UserEntity(), String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
