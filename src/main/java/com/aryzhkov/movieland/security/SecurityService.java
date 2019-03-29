package com.aryzhkov.movieland.security;

import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.Credential;

public interface SecurityService {

    Session newSession(Credential credential);

    void removeSession(String token);
}
