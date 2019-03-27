package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.*;
import com.aryzhkov.movieland.web.util.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieEnrichmentServiceImpl implements MovieEnrichmentService {

    private final GenreService genreService;

    private final CountryService countryService;

    private final ReviewService reviewService;

    private final CurrencyService currencyService;

    @Override
    public void enrich(Movie movie) {
        genreService.enrich(movie);
        countryService.enrich(movie);
        reviewService.enrich(movie);
    }

    @Override
    public void enrich(Movie movie, Currency currency) {
        movie.setPrice(currencyService.convert(movie.getPrice(), currency));
    }
}
