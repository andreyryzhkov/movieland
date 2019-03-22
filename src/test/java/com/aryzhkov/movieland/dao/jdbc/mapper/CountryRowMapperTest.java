package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.Country;
import com.aryzhkov.movieland.entity.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        CountryRowMapper countryRowMapper = new CountryRowMapper();

        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("COUNTRY_ID")).thenReturn(1);
        when(resultSetMock.getString("COUNTRY_NAME")).thenReturn("США");

        Country actualCountry = countryRowMapper.mapRow(resultSetMock,0);

        assertEquals(1, actualCountry.getId());
        assertEquals("США", actualCountry.getName());

    }
}