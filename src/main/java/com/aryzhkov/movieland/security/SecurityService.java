package com.aryzhkov.movieland.security;

import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.Credential;

import java.util.Optional;

public interface SecurityService {

    Session newSession(Credential credential);

    void removeSession(String token);

    Optional<Session> getSession(String token);
}
