package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;

public interface CountryService {

    void enrich(Movie movie);
}
