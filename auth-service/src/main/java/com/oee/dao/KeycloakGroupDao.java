package com.oee.dao;

import org.keycloak.representations.idm.GroupRepresentation;

import java.util.List;

public interface KeycloakGroupDao {

    void addUser(String group, String username);

    void removeUser(String group, String username);

    List<GroupRepresentation> findAll();
}
