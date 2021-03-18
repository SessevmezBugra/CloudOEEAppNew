package com.oee.common;

import com.oee.model.KeycloakAdminClientConfig;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServicesTestSupport {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Autowired
    private KeycloakAdminClientConfig config;


    protected HttpHeaders getHeader() {
        Keycloak keycloak =
                KeycloakBuilder.builder() //
                        .serverUrl(config.getServerUrl()) //
                        .realm(config.getRealm()) //
                        .grantType(OAuth2Constants.PASSWORD)
                        .clientId("ui-app")
                        .username("test@gmail.com")
                        .password("test")
                        .resteasyClient(
                                new ResteasyClientBuilder()
                                        .connectionPoolSize(10).build()
                        )
                        .build();
        System.out.println(config.toString());
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.add("Authorization", "Bearer " + keycloak.tokenManager().getAccessToken().getToken());
        return headers;
    }
}
