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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SecurityService securityService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SessionDto login(@RequestBody Credential credential) {
        Optional<Session> session = securityService.login(credential);

        if (session.isPresent()) {
            return new SessionDto(session.get().getToken(), session.get().getUser().getNickName());
        }

        //TODO: return JSON for exception
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username/password");
    }

    @DeleteMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void logout(@RequestHeader(value = "token") String token) {
        securityService.logout(token);
    }
}