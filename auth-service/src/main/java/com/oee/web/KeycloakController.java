package com.oee.web;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.oee.config.CurrentUserProvider;
import com.oee.dto.CurrentUser;
import com.oee.entity.UserEntity;
import com.oee.enums.UserRole;
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
        return ResponseEntity.ok(keycloakAdminClientService.addUserToCompanyOwnerGroup());
    }

    @RequestMapping(path = "/add-operator", method= RequestMethod.POST)
    public ResponseEntity<Boolean> addOperator(@RequestBody CurrentUser userDto) {
        List<String> roles = new ArrayList<>();
        roles.add(UserRole.OPERATOR.name());
        userDto.setRoles(roles);
        return ResponseEntity.ok(keycloakAdminClientService.createUser(userDto));
    }

    @RequestMapping(path = "/add-plantmanager", method= RequestMethod.POST)
    public ResponseEntity<Boolean> addPlantManager(@RequestBody CurrentUser userDto) {
        List<String> roles = new ArrayList<>();
        roles.add(UserRole.PLANT_MANAGER.name());
        userDto.setRoles(roles);
        return ResponseEntity.ok(keycloakAdminClientService.createUser(userDto));
    }

    @RequestMapping(path = "/add-clientmanager", method= RequestMethod.POST)
    public ResponseEntity<Boolean> addClientManager(@RequestBody CurrentUser userDto) {
        List<String> roles = new ArrayList<>();
        roles.add(UserRole.CLIENT_MANAGER.name());
        userDto.setRoles(roles);
        return ResponseEntity.ok(keycloakAdminClientService.createUser(userDto));
    }

    @RequestMapping(path = "/add-companyowner", method= RequestMethod.POST)
    public ResponseEntity<Boolean> addCompanyOwner(@RequestBody CurrentUser userDto) {
        List<String> roles = new ArrayList<>();
        roles.add(UserRole.COMPANY_OWNER.name());
        userDto.setRoles(roles);
        return ResponseEntity.ok(keycloakAdminClientService.createUser(userDto));
    }

    @RequestMapping(path = "/users", method= RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> getAllUsersByLoggedUser() {
        return ResponseEntity.ok(keycloakAdminClientService.findAllUsersByLoggedUser());
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