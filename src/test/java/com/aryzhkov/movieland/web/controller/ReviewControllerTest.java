package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.web.util.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml", "file:src/main/webapp/WEB-INF/movieland-servlet.xml", "classpath:spring/app-context.xml"})
@WebAppConfiguration
public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Qualifier("securityService")
    @Autowired
    private SecurityService securityService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addReview() throws Exception {
        User user = new User();
        user.setId(4);
        user.setUserRole(UserRole.USER);
        Session session = new Session();
        session.setToken("99a36356-9664-4aed-929e-fc207160e5e0");
        session.setUser(user);
        session.setExpireDate(LocalDateTime.now());

        when(securityService.getSession("99a36356-9664-4aed-929e-fc207160e5e0")).thenReturn(Optional.of(session));

        mockMvc.perform(post("/review").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"movieId\" : 55,\"text\" : \"Отличный фильм\"}")
                .header("token", "99a36356-9664-4aed-929e-fc207160e5e0"))
                .andExpect(status().isOk());
    }
}