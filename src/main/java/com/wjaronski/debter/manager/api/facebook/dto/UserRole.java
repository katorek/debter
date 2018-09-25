package com.wjaronski.debter.manager.api.facebook.dto;

/**
 * Created by Wojciech Jaronski
 */

public enum UserRole {
    ADMIN("administrators"),
    DEVELOPER("developers"),
    USER("user");

    private String facebookRole;

    UserRole(String user) {
        facebookRole = user;
    }

    public static UserRole getFromFacebookRole(String facebookRole) {
        for (UserRole value : UserRole.values()) {
            if (value.facebookRole.equals(facebookRole)) return value;
        }
        return null;
    }
}
