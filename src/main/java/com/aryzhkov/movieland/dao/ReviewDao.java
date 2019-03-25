package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getByMovieId(int id);
}
