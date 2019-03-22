package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.GenreDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.aryzhkov.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcGenreDao implements GenreDao {

    private static final String SELECT_ALL_GENRE = "SELECT genre_id, genre_name FROM movieland.ref_genre";
    private static final String SELECT_BY_ID = "SELECT rg.genre_id, rg.genre_name " +
            "FROM movieland.ref_genre rg, movieland.movie_genre_map mg " +
            "WHERE rg.genre_id = mg.genre_id AND mg.movie_id = ?";

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(SELECT_ALL_GENRE, GENRE_ROW_MAPPER);
    }

    public List<Genre> getById(int id) {
        return jdbcTemplate.query(SELECT_BY_ID, GENRE_ROW_MAPPER, id);
    }
}