package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();

        movie.setId(resultSet.getInt("MOVIE_ID"));
        movie.setNameRussian(resultSet.getString("RUSSIAN_NAME"));
        movie.setNameNative(resultSet.getString("NATIVE_NAME"));
        movie.setYearOfRelease(resultSet.getString("RELEASE_YEAR"));
        movie.setRating(resultSet.getDouble("RATING"));
        movie.setPrice(resultSet.getDouble("PRICE"));
        movie.setPicturePath(resultSet.getString("PICTURE_PATH"));

        return movie;
    }
}
