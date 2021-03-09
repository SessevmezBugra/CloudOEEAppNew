package com.oee.service;

public interface KeycloakGroupService {

    void addUser(String groupName, String username);

    void removeUser(String groupName, String username);

}
