package com.aryzhkov.movieland.dao.jdbc.mapper;

import com.aryzhkov.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("USER_ID"));
        user.setNickName(resultSet.getString("NICK_NAME"));

        return user;
    }
}
