package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.config.CurrentUserProvider;
import com.oee.dto.CurrentUser;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oee.config.KeycloakAdminClientConfig;
import com.oee.util.KeycloakAdminClientUtils;

import javax.ws.rs.core.Response;

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

    public Boolean addUserToGroup(String userId, String groupName){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(currentUserProvider.getCurrentUser().getUserId());
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals("COMPANY_OWNER")).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
        return Boolean.TRUE;
    }

    public Boolean createUser(CurrentUser userDto){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersRessource = realmResource.users();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        Response response = usersRessource.create(userRepresentation);

        String userId = CreatedResponseUtil.getCreatedId(response);
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        UserResource userResource = usersRessource.get(userId);
        // Set password credential
        userResource.resetPassword(passwordCred);

        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals("OPERATOR")).map(GroupRepresentation::getId).collect(Collectors.toList());
        List<String> groups = new ArrayList<>();
        groups.add(groupId.get(0));
        userRepresentation.setGroups(groups);

        return Boolean.TRUE;
    }


}