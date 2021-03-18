package com.oee.dao.common;

import com.oee.model.KeycloakAdminClientConfig;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;


@RequiredArgsConstructor
public abstract class KeycloakResource {

    private final Keycloak keycloak;
    private final KeycloakAdminClientConfig keycloakAdminClientConfig;

    public UsersResource getUsersResource() {
        return keycloak.realm(keycloakAdminClientConfig.getRealm()).users();
    }

    public RealmResource getRealmResource() {
        return keycloak.realm(keycloakAdminClientConfig.getRealm());
    }

    public GroupsResource getGroupResource() {
        return keycloak.realm(keycloakAdminClientConfig.getRealm()).groups();
    }

}
