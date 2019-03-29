package com.aryzhkov.movieland.security.util;

import com.aryzhkov.movieland.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Session {

    private String token;
    private User user;
    private LocalDateTime expireDate;

    public boolean isSessionExpired() {
        return !getExpireDate().isAfter(LocalDateTime.now());
    }
}
