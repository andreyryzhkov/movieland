package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml"})
@WebAppConfiguration
public class MovieControllerTest {

    @Autowired
    @Qualifier("movieServiceMock")
    private MovieService movieServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testGetAll() throws Exception {
        Mockito.reset(movieServiceMock);
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Movie movie = new Movie();

        movie.setId(1);
        movie.setNameNative("Moon");
        movie.setNameRussian("Луна");
        movie.setRating(9.8);
        movie.setPrice(10.01);
        movie.setYearOfRelease("1994");
        movie.setPicturePath("http:/path.html");

        when(movieServiceMock.getAll()).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameNative", is("Moon")))
                .andExpect(jsonPath("$[0].nameRussian", is("Луна")))
                .andExpect(jsonPath("$[0].price", is(10.01)))
                .andExpect(jsonPath("$[0].rating", is(9.8)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1994")))
                .andExpect(jsonPath("$[0].picturePath", is("http:/path.html")));
    }
}