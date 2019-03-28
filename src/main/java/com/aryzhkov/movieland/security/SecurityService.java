package com.aryzhkov.movieland.security;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.Credential;

public interface SecurityService {

    User login(Credential credential);

    Session getSession(User user);
}
