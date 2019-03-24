package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;

public interface ReviewService {

    void enrich(Movie movie);
}
