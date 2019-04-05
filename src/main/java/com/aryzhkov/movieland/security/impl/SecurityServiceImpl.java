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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    private final UserService userService;

    @Override
    public Optional<Session> login(Credential credential) {
        User user = userService.getByEmail(credential);
        if (user != null) {
            if (passwordEncoder.matches(credential.getPassword(), user.getPassword())) {

                String token = UUID.randomUUID().toString();
                Session session = new Session(token, user, LocalDateTime.now().plusHours(expireTime));

                sessions.put(token, session);

                return Optional.of(session);
            }
        }
        return Optional.empty();
    }

    @Override
    public void logout(String token) {
        sessions.remove(token);
    }

    @Override
    public Optional<Session> getSession(String token) {
        Session session = sessions.get(token);
        if (isSessionExpired(session)) {
            return Optional.empty();
        }
        return Optional.of(session);
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