package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.GenreDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.aryzhkov.movieland.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreDao implements GenreDao {

    private static final String SELECT_ALL_GENRE = "SELECT genre_id, genre_name FROM movieland.ref_genre";

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(SELECT_ALL_GENRE, GENRE_ROW_MAPPER);
    }
}
