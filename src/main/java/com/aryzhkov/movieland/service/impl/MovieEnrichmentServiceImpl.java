package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieEnrichmentServiceImpl implements MovieEnrichmentService {

    private final GenreService genreService;

    private final CountryService countryService;

    private final ReviewService reviewService;

    @Override
    public void enrich(Movie movie) {
        genreService.enrich(movie);
        countryService.enrich(movie);
        reviewService.enrich(movie);
    }
}
