package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.UserDao;
import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.service.UserService;
import com.aryzhkov.movieland.web.util.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User getByEmail(Credential credential) {
        return userDao.getByEmail(credential);
    }
}
