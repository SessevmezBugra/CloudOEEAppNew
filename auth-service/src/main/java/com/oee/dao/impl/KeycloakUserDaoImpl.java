package com.oee.dao.impl;

import com.oee.config.KeycloakAdminClientConfig;
import com.oee.dao.KeycloakUserDao;
import com.oee.dao.common.KeycloakResource;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeycloakUserDaoImpl extends KeycloakResource implements KeycloakUserDao {

    public KeycloakUserDaoImpl(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig) {
        super(keycloak, keycloakAdminClientConfig);
    }


    @Override
    public List<UserRepresentation> findByUsername(String username) {
        UsersResource usersResource = getUsersResource();
        return usersResource.search(username);
    }

    @Override
    public UserRepresentation findByUserId(String userId) {
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId).toRepresentation();
    }
}
