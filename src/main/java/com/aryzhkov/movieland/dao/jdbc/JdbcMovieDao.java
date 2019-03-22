package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.MovieFullRowMapper;
import com.aryzhkov.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.aryzhkov.movieland.dao.jdbc.util.QueryConstructor;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.util.MovieRequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private static final String SELECT_ALL_MOVIE = "SELECT movie_id, native_name, russian_name, " +
            "release_year, rating, price, currency, picture_path FROM movieland.movie";

    private static final String SELECT_RANDOM_MOVIE = "SELECT movie_id, native_name, russian_name, release_year, " +
            "rating, price, currency, picture_path FROM movieland.movie ORDER BY RANDOM() LIMIT ?";

    private static final String SELECT_BY_GENRE = "SELECT m.movie_id, m.native_name, m.russian_name, m.release_year, " +
            "m.rating, m.price, m.currency, m.picture_path FROM movieland.movie m, movieland.movie_genre_map g " +
            "WHERE m.movie_id = g.movie_id AND g.genre_id = ?";

    private static final String SELECT_BY_ID = "SELECT movie_id, native_name, russian_name, release_year, " +
            "description, rating, price, currency, picture_path FROM movieland.movie WHERE movie_id = ?";

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final MovieFullRowMapper MOVIE_FULL_ROW_MAPPER = new MovieFullRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(SELECT_ALL_MOVIE, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandom(int randomLimit) {
        return jdbcTemplate.query(SELECT_RANDOM_MOVIE, MOVIE_ROW_MAPPER, randomLimit);
    }

    @Override
    public List<Movie> getByGenre(int id) {
        return jdbcTemplate.query(SELECT_BY_GENRE, MOVIE_ROW_MAPPER, id);
    }

    @Override
    public List<Movie> getAll(MovieRequestParam movieRequestParam) {
        return jdbcTemplate.query(QueryConstructor.Query(SELECT_ALL_MOVIE, movieRequestParam), MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getByGenre(int id, MovieRequestParam movieRequestParam) {
        return jdbcTemplate.query(QueryConstructor.Query(SELECT_BY_GENRE, movieRequestParam), MOVIE_ROW_MAPPER, id);
    }

    @Override
    public Movie getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, MOVIE_FULL_ROW_MAPPER, id);
    }
}