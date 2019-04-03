package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.web.util.Credential;

public interface UserDao {

    User getByEmail(Credential credential);
}
