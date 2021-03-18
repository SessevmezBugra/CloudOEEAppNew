package com.oee.web;


import com.oee.entity.UserEntity;
import com.oee.service.KeycloakUserService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiPaths.UserCtrl.CTRL)
@RequiredArgsConstructor
public class UserController {

    private final KeycloakUserService keycloakUserService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserRepresentation>> getUserByUsername(@RequestParam(value = "username", required = true) String username) {
        return ResponseEntity.ok(keycloakUserService.findByUsername(username));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserRepresentation> getUserByUserId(@PathVariable(value = "userId", required = true) String userId) {
        return ResponseEntity.ok(keycloakUserService.findByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserEntity> testPost(@RequestBody UserEntity userEntity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object currentPrincipalName = authentication.getPrincipal();
        System.out.println(authentication.getName());
        System.out.println(currentPrincipalName.toString());
        return ResponseEntity.ok(userEntity);
    }

}
