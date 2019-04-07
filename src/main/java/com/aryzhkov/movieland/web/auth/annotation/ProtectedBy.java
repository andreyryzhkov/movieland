package com.aryzhkov.movieland.web.auth.annotation;

import com.aryzhkov.movieland.web.util.UserRole;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ProtectedBy {
    UserRole[] value() default UserRole.ADMIN;
}
