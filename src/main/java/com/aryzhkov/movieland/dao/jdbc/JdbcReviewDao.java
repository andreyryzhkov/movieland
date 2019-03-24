package com.aryzhkov.movieland.dao.jdbc;

import com.aryzhkov.movieland.dao.ReviewDao;
import com.aryzhkov.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.aryzhkov.movieland.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcReviewDao implements ReviewDao {

    private static final String SELECT_ALL = "select mr.review_id, mr.comment, mu.user_id, " +
            "mu.first_name || ' ' || mu.last_name as nick_name " +
            "from movieland.review mr, movieland.users mu " +
            "where mr.user_id = mu.user_id and movie_id = ?";

    private static final ReviewRowMapper REVIEW_ROW_MAPPER = new ReviewRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> getByMovieId(int id) {
        return jdbcTemplate.query(SELECT_ALL, REVIEW_ROW_MAPPER, id);
    }
}