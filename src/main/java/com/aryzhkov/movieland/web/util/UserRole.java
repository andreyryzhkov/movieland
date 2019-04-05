package com.aryzhkov.movieland.web.util;

public enum UserRole {

    USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public static UserRole getByRole(String role) {
        UserRole[] userRoles = UserRole.values();
        for (UserRole userRole : userRoles) {
            if (userRole.getRole().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("No such user role " + role);
    }

    public String getRole() {
        return role;
    }
}
