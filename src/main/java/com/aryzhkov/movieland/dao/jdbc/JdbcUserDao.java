package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.UserDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.UserRowMapper;
import com.aryzhkov.movieland.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcUserDao implements UserDao {

    private static final String SELECT_ALL = "select user_id, first_name, last_name, email, password from movieland.users";

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SELECT_ALL, USER_ROW_MAPPER);
    }
}