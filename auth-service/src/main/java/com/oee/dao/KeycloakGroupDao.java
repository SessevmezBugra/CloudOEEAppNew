package com.oee.dao;

public interface KeycloakGroupDao {

    void addUser(String group, String username);

    void removeUser(String group, String username);
}
