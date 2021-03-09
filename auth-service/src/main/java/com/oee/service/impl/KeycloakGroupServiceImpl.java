package com.oee.service.impl;

import com.oee.dao.KeycloakGroupDao;
import com.oee.service.KeycloakGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakGroupServiceImpl implements KeycloakGroupService {

    private final KeycloakGroupDao keycloakGroupDao;

    @Override
    public void addUser(String groupName, String username) {
        keycloakGroupDao.addUser(groupName, username);
    }

    @Override
    public void removeUser(String groupName, String username) {

    }




}
