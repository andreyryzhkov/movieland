package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.UserDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.UserRowMapper;
import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.web.util.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcUserDao implements UserDao {

    private static final String SELECT_BY_EMAIL = "select user_id, first_name || ' ' || last_name as nick_name" +
            "  from movieland.users where email = ? and password = ?";

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User getByEmail(Credential credential) {
        return jdbcTemplate.queryForObject(SELECT_BY_EMAIL, USER_ROW_MAPPER, credential.getLogin(), credential.getPassword());
    }
}
