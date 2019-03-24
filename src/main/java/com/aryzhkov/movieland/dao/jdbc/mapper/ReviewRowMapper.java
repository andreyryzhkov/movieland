package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.Review;
import com.aryzhkov.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("USER_ID"));
        user.setNickName(resultSet.getString("NICK_NAME"));

        Review review = new Review();
        review.setId(resultSet.getInt("REVIEW_ID"));
        review.setComment(resultSet.getString("COMMENT"));
        review.setUser(user);

        return review;
    }
}
