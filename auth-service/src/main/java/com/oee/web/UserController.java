package com.oee.web;


import com.oee.service.KeycloakUserService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = ApiPaths.UserCtrl.CTRL)
@RequiredArgsConstructor
public class UserController {

    private final KeycloakUserService keycloakUserService;

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<UserRepresentation>> getUserByUsername(@PathVariable(value = "username", required = true) String username) {
        return ResponseEntity.ok(keycloakUserService.findByUsername(username));
    }

    @RequestMapping(value = "/user-id/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserRepresentation> getUserByUserId(@PathVariable(value = "userId", required = true) String userId) {
        return ResponseEntity.ok(keycloakUserService.findByUserId(userId));
    }

}
