package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        GenreRowMapper genreRowMapper = new GenreRowMapper();

        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("GENRE_ID")).thenReturn(1);
        when(resultSetMock.getString("GENRE_NAME")).thenReturn("драма");

        Genre actualGenre = genreRowMapper.mapRow(resultSetMock,0);

        assertEquals(1, actualGenre.getId());
        assertEquals("драма", actualGenre.getName());
    }
}
