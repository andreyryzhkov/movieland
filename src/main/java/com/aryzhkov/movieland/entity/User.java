package com.aryzhkov.movieland.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getNickName() {
        return firstName + " " + lastName;
    }
}