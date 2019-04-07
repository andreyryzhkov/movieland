package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.CountryService;
import com.aryzhkov.movieland.service.GenreService;
import com.aryzhkov.movieland.service.MovieEnrichmentService;
import com.aryzhkov.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ParallelMovieEnrichmentServiceImpl implements MovieEnrichmentService {

    private final GenreService genreService;

    private final CountryService countryService;

    private final ReviewService reviewService;

    @Value("${movie.enrichment.timeout:5}")
    private int timeout;

    @Value("${movie.enrichment.threads:3}")
    private int threads;

    @Override
    public void enrich(Movie movie) {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        Set<Callable<Void>> callables = new HashSet<>();

        callables.add(() -> {
            genreService.enrich(movie);
            return null;
        });

        callables.add(() -> {
            countryService.enrich(movie);
            return null;
        });

        callables.add(() -> {
            reviewService.enrich(movie);
            return null;
        });

        try {
            log.info("Parallel movie enrichment");
            executorService.invokeAll(callables, timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Movie enrichment was interrupted ", e);
        }

        executorService.shutdown();
    }
}
