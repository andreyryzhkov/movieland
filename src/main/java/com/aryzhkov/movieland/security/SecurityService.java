package com.aryzhkov.movieland.security;

import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.Credential;

import java.util.Optional;

public interface SecurityService {

    Optional<Session> login(Credential credential);

    void logout(String token);
}
