package com.oee.service;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakUserService {
    List<UserRepresentation> findByUsername(String username);

    UserRepresentation findByUserId(String userId);
}
