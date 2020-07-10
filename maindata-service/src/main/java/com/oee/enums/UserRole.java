package com.oee.enums;

public enum UserRole {
    OPERATOR("ROLE_OPERATOR"),
    PLANT_MANAGER("ROLE_PLANT_MANAGER"),
    CLIENT_MANAGER("ROLE_CLIENT_MANAGER"),
    COMPANY_OWNER("ROLE_COMPANY_OWNER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
