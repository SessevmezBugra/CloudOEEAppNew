package com.oee.web;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oee.service.impl.KeycloakAdminClientService;

@RestController
@RequestMapping(path = "/keycloak")
public class KeycloakController {

    @Autowired
    private KeycloakAdminClientService keycloakAdminClientService;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(path = "/roles")
    public Collection<String> rolesOfCurrentUser() {
        return keycloakAdminClientService.getCurrentUserRoles();
    }

    @GetMapping(path = "/profile")
    public Object profileOfCurrentUser() {
        return keycloakAdminClientService.getUserProfileOfLoggedUser();
    }
}