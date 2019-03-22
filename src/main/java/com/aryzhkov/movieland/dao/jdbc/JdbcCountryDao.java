package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.CountryDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.aryzhkov.movieland.entity.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcCountryDao implements CountryDao {

    private static final String SELECT_ALL = "select rc.country_id, rc.country_name " +
            "from movieland.ref_country rc, movieland.movie_country_map mc " +
            "where rc.country_id = mc.country_id and mc.movie_id = ?";

    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    private final JdbcTemplate jdbcTemplate;

    public List<Country> getByMovieId(int id) {
        return jdbcTemplate.query(SELECT_ALL, COUNTRY_ROW_MAPPER, id);
    }
}