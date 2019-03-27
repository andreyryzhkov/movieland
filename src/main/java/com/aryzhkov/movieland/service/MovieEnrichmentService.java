package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;

public interface MovieEnrichmentService {

    void enrich(Movie movie);
}
