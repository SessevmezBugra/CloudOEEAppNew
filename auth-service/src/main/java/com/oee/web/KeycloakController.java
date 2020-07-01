package com.oee.web;
import java.util.Collection;

import com.oee.config.CurrentUserProvider;
import com.oee.dto.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oee.service.impl.KeycloakAdminClientService;

@RestController
@RequestMapping(path = "/auth/keycloak")
public class KeycloakController {

    private final KeycloakAdminClientService keycloakAdminClientService;
    private final CurrentUserProvider currentUserProvider;

    public KeycloakController(KeycloakAdminClientService keycloakAdminClientService, CurrentUserProvider currentUserProvider) {
        this.keycloakAdminClientService = keycloakAdminClientService;
        this.currentUserProvider = currentUserProvider;
    }

    @RequestMapping(path = "/company-owner", method= RequestMethod.POST)
    public ResponseEntity<Boolean> addCompanyOwnerRole() {
        return ResponseEntity.ok(keycloakAdminClientService.addUserToGroup(currentUserProvider.getCurrentUser().getUserId(),"COMPANY_OWNER"));
    }

    @RequestMapping(path = "/add-operator", method= RequestMethod.POST)
    public ResponseEntity<Boolean> addOperator(@RequestBody CurrentUser userDto) {
        return ResponseEntity.ok(keycloakAdminClientService.createUser(userDto));
    }

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