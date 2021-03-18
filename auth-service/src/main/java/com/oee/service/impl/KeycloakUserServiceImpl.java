package com.oee.service.impl;

import com.oee.dao.KeycloakUserDao;
import com.oee.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private final KeycloakUserDao keycloakUserDao;

    @Override
    public List<UserRepresentation> findByUsername(String username) {
        if(ObjectUtils.isEmpty(username)){
            return Collections.emptyList();
        }
        return keycloakUserDao.findByUsername(username);
    }

    @Override
    public UserRepresentation findByUserId(String userId) {
        return keycloakUserDao.findByUserId(userId);
    }

}
