package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.web.util.Credential;

public interface UserService {

    User getByEmail(Credential credential);
}
