package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.dto.SessionDto;
import com.aryzhkov.movieland.web.util.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SecurityService securityService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SessionDto login(@RequestBody Credential credential) {
        Session session = securityService.newSession(credential);

        if (session != null) {
            return new SessionDto(session.getToken(), session.getUser().getNickName());
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username/password");
    }

    @DeleteMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void logout(@RequestHeader(value = "token") String token) {
        securityService.removeSession(token);
    }
}