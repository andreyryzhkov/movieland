package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.*;
import com.aryzhkov.movieland.web.util.Currency;
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
        fullMovie.setNameNative("Intouchables");
        fullMovie.setNameRussian("1+1");
        fullMovie.setRating(8.3);
        fullMovie.setPrice(120);
        fullMovie.setYearOfRelease("2011");
        fullMovie.setDescription("Краткое содержание");
        fullMovie.setPicturePath("http:/path/picture.jpg");
        fullMovie.setCountries(Arrays.asList(new Country(2, "Франция")));
        fullMovie.setGenres(Arrays.asList(new Genre(1, "драма"), new Genre(6, "биография"),
                new Genre(7, "комедия")));
        fullMovie.setReviews(Arrays.asList(new Review(9, "Комментарий1",
                new User(10, "Амелия Кэннеди")), new Review(8, "Комментарий2",
                new User(12, "Джесси Паттерсон"))));
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
                .andExpect(jsonPath("$.id", is(55)))
                .andExpect(jsonPath("$.nameNative", is("Intouchables")))
                .andExpect(jsonPath("$.nameRussian", is("1+1")))
                .andExpect(jsonPath("$.price", is(120.0)))
                .andExpect(jsonPath("$.rating", is(8.3)))
                .andExpect(jsonPath("$.yearOfRelease", is("2011")))
                .andExpect(jsonPath("$.picturePath", is("http:/path/picture.jpg")))
                .andExpect(jsonPath("$.description", is("Краткое содержание")))
                .andExpect(jsonPath("$.countries", hasSize(1)))
                .andExpect(jsonPath("$.countries[0].id", is(2)))
                .andExpect(jsonPath("$.countries[0].name", is("Франция")))
                .andExpect(jsonPath("$.genres", hasSize(3)))
                .andExpect(jsonPath("$.genres[0].id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("драма")))
                .andExpect(jsonPath("$.genres[1].id", is(6)))
                .andExpect(jsonPath("$.genres[1].name", is("биография")))
                .andExpect(jsonPath("$.genres[2].id", is(7)))
                .andExpect(jsonPath("$.genres[2].name", is("комедия")))
                .andExpect(jsonPath("$.reviews", hasSize(2)))
                .andExpect(jsonPath("$.reviews[0].id", is(9)))
                .andExpect(jsonPath("$.reviews[0].comment", is("Комментарий1")))
                .andExpect(jsonPath("$.reviews[0].user.id", is(10)))
                .andExpect(jsonPath("$.reviews[0].user.nickName", is("Амелия Кэннеди")))
                .andExpect(jsonPath("$.reviews[1].id", is(8)))
                .andExpect(jsonPath("$.reviews[1].comment", is("Комментарий2")))
                .andExpect(jsonPath("$.reviews[1].user.id", is(12)))
                .andExpect(jsonPath("$.reviews[1].user.nickName", is("Джесси Паттерсон")));
    }

    @Test
    public void testGetByIdCurrency() throws Exception {
        when(movieServiceMock.getById(55, Currency.USD)).thenReturn(fullMovie);

        mockMvc.perform(get("/movie/55?currency=USD"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(55)))
                .andExpect(jsonPath("$.nameNative", is("Intouchables")))
                .andExpect(jsonPath("$.nameRussian", is("1+1")))
                .andExpect(jsonPath("$.price", is(120.0)))
                .andExpect(jsonPath("$.rating", is(8.3)))
                .andExpect(jsonPath("$.yearOfRelease", is("2011")))
                .andExpect(jsonPath("$.picturePath", is("http:/path/picture.jpg")))
                .andExpect(jsonPath("$.description", is("Краткое содержание")))
                .andExpect(jsonPath("$.countries", hasSize(1)))
                .andExpect(jsonPath("$.countries[0].id", is(2)))
                .andExpect(jsonPath("$.countries[0].name", is("Франция")))
                .andExpect(jsonPath("$.genres", hasSize(3)))
                .andExpect(jsonPath("$.genres[0].id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("драма")))
                .andExpect(jsonPath("$.genres[1].id", is(6)))
                .andExpect(jsonPath("$.genres[1].name", is("биография")))
                .andExpect(jsonPath("$.genres[2].id", is(7)))
                .andExpect(jsonPath("$.genres[2].name", is("комедия")))
                .andExpect(jsonPath("$.reviews", hasSize(2)))
                .andExpect(jsonPath("$.reviews[0].id", is(9)))
                .andExpect(jsonPath("$.reviews[0].comment", is("Комментарий1")))
                .andExpect(jsonPath("$.reviews[0].user.id", is(10)))
                .andExpect(jsonPath("$.reviews[0].user.nickName", is("Амелия Кэннеди")))
                .andExpect(jsonPath("$.reviews[1].id", is(8)))
                .andExpect(jsonPath("$.reviews[1].comment", is("Комментарий2")))
                .andExpect(jsonPath("$.reviews[1].user.id", is(12)))
                .andExpect(jsonPath("$.reviews[1].user.nickName", is("Джесси Паттерсон")));

    }
}