package com.oee.service.impl;

import com.oee.dao.KeycloakGroupDao;
import com.oee.service.KeycloakGroupService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakGroupServiceImpl implements KeycloakGroupService {

    private final KeycloakGroupDao keycloakGroupDao;

    @Override
    public void addUser(String groupId, String userId) {
        keycloakGroupDao.addUser(groupId, userId);
    }

    @Override
    public void removeUser(String groupId, String userId) {

    }

    @Override
    public List<GroupRepresentation> findAll() {
        return keycloakGroupDao.findAll();
    }


}
