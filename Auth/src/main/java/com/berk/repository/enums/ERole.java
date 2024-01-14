package com.berk.repository.enums;


public enum ERole {
    ADMIN,USER,EXECUTIVE;

    public static ERole fromString(String roleStr) {
        for (ERole role : ERole.values()) {
            if (role.name().equalsIgnoreCase(roleStr)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + roleStr + " found!");
    }
}