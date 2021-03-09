package com.oee.web;

import com.oee.service.KeycloakGroupService;
import com.oee.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ApiPaths.GroupCtrl.CTRL)
@RequiredArgsConstructor
public class GroupController {

    private final KeycloakGroupService keycloakGroupService;

    @RequestMapping(value="/{groupName}/user/{userName}", method= RequestMethod.PUT)
    public ResponseEntity addUserToGroup(@PathVariable(value="groupName", required=true) String groupName, @PathVariable(value="userName", required=true) String userName){
        keycloakGroupService.addUser(groupName, userName);
        return ResponseEntity.ok().build();
    }
}
