package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.Country;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.util.MovieRequestParam;
import com.aryzhkov.movieland.web.util.SortOrder;
import com.aryzhkov.movieland.service.MovieService;
import org.junit.Before;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml", "file:src/main/webapp/WEB-INF/movieland-servlet.xml", "classpath:spring/app-context.xml"})
@WebAppConfiguration
public class MovieControllerTest {

    private MockMvc mockMvc;

    private Movie movie = new Movie();
    private Movie fullMovie = new Movie();

    @Qualifier("movieService")
    @Autowired
    private MovieService movieServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(movieServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        movie.setId(1);
        movie.setNameNative("Moon");
        movie.setNameRussian("Луна");
        movie.setRating(9.8);
        movie.setPrice(10.01);
        movie.setYearOfRelease("1994");
        movie.setPicturePath("http:/path.html");

        fullMovie.setId(55);
    }

    @Test
    public void testGetAll() throws Exception {
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

    @Test
    public void testGetRandom() throws Exception {
        when(movieServiceMock.getRandom()).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie/random"))
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

    @Test
    public void testGetByGenre() throws Exception {
        when(movieServiceMock.getByGenre(1)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie/genre/1"))
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

    @Test
    public void testGetAllOrder() throws Exception {
        MovieRequestParam movieRequestParam = new MovieRequestParam("rating", SortOrder.DESC);

        when(movieServiceMock.getAll(movieRequestParam)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie?rating=desc"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameNative", is("Moon")))
                .andExpect(jsonPath("$[0].nameRussian", is("Луна")))
                .andExpect(jsonPath("$[0].price", is(10.01)))
                .andExpect(jsonPath("$[0].rating", is(9.8)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1994")))
                .andExpect(jsonPath("$[0].picturePath", is("http:/path.html")));
    }

    @Test
    public void testGetByGenreOrder() throws Exception {
        MovieRequestParam movieRequestParam = new MovieRequestParam("price", SortOrder.ASC);

        when(movieServiceMock.getByGenre(1, movieRequestParam)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie/genre/1?price=ASC"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameNative", is("Moon")))
                .andExpect(jsonPath("$[0].nameRussian", is("Луна")))
                .andExpect(jsonPath("$[0].price", is(10.01)))
                .andExpect(jsonPath("$[0].rating", is(9.8)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1994")))
                .andExpect(jsonPath("$[0].picturePath", is("http:/path.html")));
    }

    @Test
    public void testGetById() throws Exception {
        when(movieServiceMock.getById(55)).thenReturn(fullMovie);

        mockMvc.perform(get("/movie/55"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(55)));
    }
}