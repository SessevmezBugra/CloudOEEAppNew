package com.oee.dao.impl;

import com.oee.config.KeycloakAdminClientConfig;
import com.oee.dao.KeycloakGroupDao;
import com.oee.dao.common.KeycloakResource;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class KeycloakGroupDaoImpl extends KeycloakResource implements KeycloakGroupDao {

    public KeycloakGroupDaoImpl(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig) {
        super(keycloak, keycloakAdminClientConfig);
    }

    @Override
    public void addUser(String group, String username) {
        UsersResource usersResource = getUsersResource();
        UserResource userResource = usersResource.get(usersResource.search(username).get(0).getId());
        GroupsResource groupsResource = getGroupResource();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(group)).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
    }

    @Override
    public void removeUser(String group, String username) {

    }
}
