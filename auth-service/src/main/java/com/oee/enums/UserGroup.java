package com.oee.enums;

public enum UserGroup {
    STAFF("staff"),
    COMPANY_OWNER("company-owner");

    private final String value;

    UserGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
