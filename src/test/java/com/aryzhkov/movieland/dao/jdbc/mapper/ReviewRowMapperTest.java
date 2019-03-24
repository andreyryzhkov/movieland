package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.Review;
import com.aryzhkov.movieland.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();

        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getInt("REVIEW_ID")).thenReturn(1);
        when(resultSetMock.getString("COMMENT")).thenReturn("Comment");
        when(resultSetMock.getInt("USER_ID")).thenReturn(1);
        when(resultSetMock.getString("NICK_NAME")).thenReturn("Tom Kasper");

        Review actualReview = reviewRowMapper.mapRow(resultSetMock, 0);

        assertEquals(1, actualReview.getId());
        assertEquals("Comment", actualReview.getComment());
        assertEquals(1, actualReview.getUser().getId());
        assertEquals("Tom Kasper", actualReview.getUser().getNickName());
    }
}