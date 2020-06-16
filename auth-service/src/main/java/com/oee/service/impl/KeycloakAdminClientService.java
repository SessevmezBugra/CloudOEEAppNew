package com.oee.service.impl;

import java.util.List;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oee.config.KeycloakAdminClientConfig;
import com.oee.dto.CurrentUserProvider;
import com.oee.util.KeycloakAdminClientUtils;

@Service
public class KeycloakAdminClientService {

//    @Value("${keycloak.resource}")
    @Autowired
    private CurrentUserProvider currentUserProvider;
    
    @Autowired
    private Environment environment;


    public List<String> getCurrentUserRoles() {
        return currentUserProvider.getCurrentUser().getRoles();
    }

    public Object getUserProfileOfLoggedUser() {

        @SuppressWarnings("unchecked")
        KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal = (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(principal.getKeycloakSecurityContext(), keycloakAdminClientConfig);

        // Get realm
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(currentUserProvider.getCurrentUser().getUserId());
        UserRepresentation userRepresentation = userResource.toRepresentation();

        return userRepresentation;
    }
}