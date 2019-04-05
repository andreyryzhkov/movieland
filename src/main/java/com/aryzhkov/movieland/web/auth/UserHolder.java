package com.aryzhkov.movieland.web.auth;

import com.aryzhkov.movieland.entity.User;

public class UserHolder {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        userHolder.set(user);
    }

    public static User getCurrentUser() {
        return userHolder.get();
    }

    public static void remove() {
        userHolder.remove();
    }
}
