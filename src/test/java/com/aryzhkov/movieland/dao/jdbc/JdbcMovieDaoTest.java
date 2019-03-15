package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.entity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

// TODO: move tests to H2 db

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/app-context.xml"})
public class JdbcMovieDaoTest {

    @Autowired
    private JdbcMovieDao jdbcMovieDao;

    @Test
    public void testGetAll() {
        List<Movie> movies = jdbcMovieDao.getAll();
        for (Movie movie : movies) {
            assertNotEquals(movie.getId(), 0);
            assertNotNull(movie.getNameNative());
            assertNotEquals(movie.getPrice(), 0);
            assertNotEquals(movie.getYearOfRelease(), "0");
        }
    }

    @Test
    public void testGetRandom() {
        List<Movie> movies = jdbcMovieDao.getRandom(3);
        for (Movie movie : movies) {
            assertNotEquals(movie.getId(), 0);
            assertNotNull(movie.getNameNative());
            assertNotEquals(movie.getPrice(), 0);
            assertNotEquals(movie.getYearOfRelease(), "0");
        }
        assertEquals(3, movies.size());
    }
}