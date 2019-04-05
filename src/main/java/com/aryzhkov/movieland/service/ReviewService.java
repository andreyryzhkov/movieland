package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.Review;

public interface ReviewService {

    void enrich(Movie movie);

    void addReview(Review review);
}
