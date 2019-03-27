package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.util.Currency;

public interface MovieEnrichmentService {

    void enrich(Movie movie);

    void enrich(Movie movie, Currency currency);
}
