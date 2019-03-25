package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieFullRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        MovieFullRowMapper movieFullRowMapper = new MovieFullRowMapper();

        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("MOVIE_ID")).thenReturn(1);
        when(resultSetMock.getString("RUSSIAN_NAME")).thenReturn("Луна");
        when(resultSetMock.getString("NATIVE_NAME")).thenReturn("Moon");
        when(resultSetMock.getString("RELEASE_YEAR")).thenReturn("1994");
        when(resultSetMock.getString("DESCRIPTION")).thenReturn("Description");
        when(resultSetMock.getDouble("RATING")).thenReturn(9.8);
        when(resultSetMock.getDouble("PRICE")).thenReturn(10.5);
        when(resultSetMock.getString("CURRENCY")).thenReturn("USD");
        when(resultSetMock.getString("PICTURE_PATH")).thenReturn("http://picture_path.html");

        Movie actualMovie = movieFullRowMapper.mapRow(resultSetMock, 0);

        assertEquals(1, actualMovie.getId());
        assertEquals("Moon", actualMovie.getNameNative());
        assertEquals("Луна", actualMovie.getNameRussian());
        assertEquals("1994", actualMovie.getYearOfRelease());
        assertEquals("Description", actualMovie.getDescription());
        assertEquals(9.8, actualMovie.getRating(), 0.00001);
        assertEquals(10.5, actualMovie.getPrice(), 0.00001);
        assertEquals("USD", actualMovie.getCurrency());
        assertEquals("http://picture_path.html", actualMovie.getPicturePath());

    }
}