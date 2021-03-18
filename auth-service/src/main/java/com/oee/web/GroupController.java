package com.oee.web;

import com.oee.service.KeycloakGroupService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiPaths.GroupCtrl.CTRL)
@RequiredArgsConstructor
public class GroupController {

    private final KeycloakGroupService keycloakGroupService;

    @RequestMapping(value="/{groupId}/user/{userId}", method= RequestMethod.PUT)
    public ResponseEntity addUserToGroup(@PathVariable(value="groupId", required=true) String groupId, @PathVariable(value="userId", required=true) String userId){
        keycloakGroupService.addUser(groupId, userId);
        throw new RuntimeException("Test");
//        return ResponseEntity.ok().build();
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<GroupRepresentation>> getAll(){
        return ResponseEntity.ok(keycloakGroupService.findAll());
    }
}
