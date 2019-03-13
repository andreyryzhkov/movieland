package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.aryzhkov.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {

    private static final String SELECT_ALL_MOVIE = "SELECT movie_id, native_name, russian_name, " +
            "release_year, description, rating, price, currency, picture_path FROM movieland.movie";

    private static final String SELECT_RANDOM_MOVIE = "SELECT movie_id, native_name, russian_name, release_year, " +
            "description, rating, price, currency, picture_path FROM movieland.movie ORDER BY RANDOM() LIMIT 3";

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(SELECT_ALL_MOVIE, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandom() {
        return jdbcTemplate.query(SELECT_RANDOM_MOVIE, MOVIE_ROW_MAPPER);
    }
}