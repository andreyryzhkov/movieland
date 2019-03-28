package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.dto.SessionDto;
import com.aryzhkov.movieland.web.util.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SecurityService securityService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SessionDto login(@RequestBody String login, String password) {
        Credential credential = new Credential(login, password);
        User user = securityService.login(credential);
        Session session = securityService.getSession(user);

        return new SessionDto(session.getToken(), session.getUser().getNickName());
    }

    @DeleteMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String logout() {
        return null;
    }
}