package com.oee.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrentUser {

    private String userId;
    private String password;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
