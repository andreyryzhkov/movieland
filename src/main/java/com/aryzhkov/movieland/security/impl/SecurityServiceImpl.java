package com.aryzhkov.movieland.security.impl;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.service.UserService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    @Value("2")
    private int expireTime;

    private List<Session> sessions = Collections.synchronizedList(new ArrayList<>());

    private final UserService userService;

    @Override
    public User login(Credential credential) {
        User user = userService.getByEmail(credential);

        return user;
    }

    @Override
    public Session getSession(User user) {
        Session session = new Session();
        String token = UUID.randomUUID().toString();

        session.setToken(token);
        session.setUser(user);
        session.setExpireDate(LocalDateTime.now().plusHours(expireTime));

        sessions.add(session);

        return session;
    }

    private void removeSession(Session session) {
        sessions.remove(session);
    }

    private boolean isSessionExpired(Session session) {
        return !session.getExpireDate().isAfter(LocalDateTime.now());
    }

    @PostConstruct
    @Scheduled(cron = "0 * * * * *")
    private void removeSession() {
        for (Session session : sessions) {
            if (isSessionExpired(session)) {
                removeSession(session);
            }
        }
    }
}