package com.oee.service;

import org.keycloak.representations.idm.GroupRepresentation;

import java.util.List;

public interface KeycloakGroupService {

    void addUser(String groupId, String userId);

    void removeUser(String groupId, String userId);

    List<GroupRepresentation> findAll();

}
