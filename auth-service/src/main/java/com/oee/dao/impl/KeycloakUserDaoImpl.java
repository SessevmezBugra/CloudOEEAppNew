package com.oee.dao.impl;

import com.oee.dao.KeycloakUserDao;
import com.oee.dao.common.KeycloakResource;
import com.oee.model.KeycloakAdminClientConfig;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Repository;

import java.util.List;

//@ConditionalOnProperty(
//        name = "custom.security.config.enabled",
//        havingValue = "true",
//        matchIfMissing = true)
@Repository
public class KeycloakUserDaoImpl extends KeycloakResource implements KeycloakUserDao {

    public KeycloakUserDaoImpl(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig) {
        super(keycloak, keycloakAdminClientConfig);
    }


    @Override
    public List<UserRepresentation> findByUsername(String username) {
        UsersResource usersResource = getUsersResource();
        return usersResource.search(username, null, null, null, true, 0, 50, true, false);
    }

    @Override
    public UserRepresentation findByUserId(String userId) {
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId).toRepresentation();
    }
}
