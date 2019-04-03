package com.aryzhkov.movieland.entity;

import com.aryzhkov.movieland.web.util.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String nickName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private UserRole userRole;
}