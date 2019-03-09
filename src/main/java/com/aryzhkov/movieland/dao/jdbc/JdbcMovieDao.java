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

    private static final String SELECT_ALL_MOVIE = "SELECT \"MOVIE_ID\", \"NATIVE_NAME\", \"RUSSIAN_NAME\"," +
            "\"RELEASE_YEAR\", \"DESCRIPTION\", \"RATING\", \"PRICE\", \"CURRENCY\", \"PICTURE_PATH\" FROM movieland.\"MOVIE\"";

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
}