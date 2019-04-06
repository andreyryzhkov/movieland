package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.MovieFullRowMapper;
import com.aryzhkov.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.aryzhkov.movieland.dao.jdbc.util.QueryConstructor;
import com.aryzhkov.movieland.entity.Country;
import com.aryzhkov.movieland.entity.Genre;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.util.MovieRequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
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

    private static final String INSERT_MOVIE = "insert into movieland.movie(native_name, russian_name, release_year, " +
            "description, price, picture_path) values (?,?,?,?,?,?)";

    private static final String INSERT_COUNTRY_MAP = "insert into movieland.movie_country_map(movie_id, country_id) " +
            "values (?,?)";

    private static final String INSERT_GENRE_MAP = "insert into movieland.movie_genre_map(movie_id, genre_id) " +
            "values (?,?)";

    private static final String UPDATE_MOVIE = "update movieland.movie set russian_name = ?, native_name = ?, " +
            "picture_path = ? where movie_id = ?";

    private static final String DELETE_COUNTRY_MAP = "delete from movieland.movie_country_map where movie_id = ?";

    private static final String DELETE_GENRE_MAP = "delete from movieland.movie_genre_map where movie_id = ?";

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

    @Transactional
    @Override
    public Movie add(Movie movie, int[] countryIds, int[] genreIds) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_MOVIE, new String[]{"movie_id"});
            ps.setString(1, movie.getNameNative());
            ps.setString(2, movie.getNameRussian());
            ps.setString(3, movie.getYearOfRelease());
            ps.setString(4, movie.getDescription());
            ps.setDouble(5, movie.getPrice());
            ps.setString(6, movie.getPicturePath());

            return ps;
        }, keyHolder);

        int movieId = keyHolder.getKey().intValue();

        for (int countryId : countryIds) {
            jdbcTemplate.update(INSERT_COUNTRY_MAP, movieId, countryId);
        }

        for (int genreId : genreIds) {
            jdbcTemplate.update(INSERT_GENRE_MAP, movieId, genreId);
        }

        movie.setId(movieId);

        return movie;
    }

    @Transactional
    @Override
    public void edit(Movie movie, int[] countryIds, int[] genreIds) {
        jdbcTemplate.update(UPDATE_MOVIE, movie.getNameRussian(), movie.getNameNative(), movie.getPicturePath(), movie.getId());

        jdbcTemplate.update(DELETE_COUNTRY_MAP, movie.getId());
        for (int countryId : countryIds) {
            jdbcTemplate.update(INSERT_COUNTRY_MAP, movie.getId(), countryId);
        }

        jdbcTemplate.update(DELETE_GENRE_MAP, movie.getId());
        for (int genreId : genreIds) {
            jdbcTemplate.update(INSERT_GENRE_MAP, movie.getId(), genreId);
        }
    }
}