package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();

        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("USER_ID")).thenReturn(1);
        when(resultSetMock.getString("FIRST_NAME")).thenReturn("Tom");
        when(resultSetMock.getString("LAST_NAME")).thenReturn("Kasper");
        when(resultSetMock.getString("EMAIL")).thenReturn("user@mail.com");
        when(resultSetMock.getString("PASSWORD")).thenReturn("pass");

        User actualUser = userRowMapper.mapRow(resultSetMock, 0);

        assertEquals(1, actualUser.getId());
        assertEquals("Tom", actualUser.getFirstName());
        assertEquals("Kasper", actualUser.getLastName());
        assertEquals("user@mail.com", actualUser.getEmail());
        assertEquals("pass", actualUser.getPassword());
    }
}