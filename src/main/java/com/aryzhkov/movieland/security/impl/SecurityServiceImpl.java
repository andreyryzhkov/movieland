package com.aryzhkov.movieland.security.impl;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.service.UserService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.Credential;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    @Value("${session.timeout:2}")
    private int expireTime;

    private final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap();

    private final UserService userService;

    @Override
    public Session newSession(Credential credential) {
        Session session = new Session();

        String token = UUID.randomUUID().toString();
        User user = userService.getByEmail(credential);

        session.setToken(token);
        session.setUser(user);
        session.setExpireDate(LocalDateTime.now().plusHours(expireTime));

        sessions.put(token, session);

        return session;
    }

    @Override
    public void removeSession(String token) {
        sessions.remove(token);
    }

    private boolean isSessionExpired(Session session) {
        return !session.getExpireDate().isAfter(LocalDateTime.now());
    }

    @PostConstruct
    @Scheduled(cron = "0 * * * * *")
    private void removeSession() {
        log.info("Refresh sessions cache");
        sessions.entrySet()
                .removeIf(entry -> isSessionExpired(entry.getValue()));
    }
}