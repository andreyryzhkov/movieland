package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.*;
import com.aryzhkov.movieland.web.util.Currency;
import com.aryzhkov.movieland.web.util.MovieRequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    private final MovieEnrichmentService movieEnrichmentService;

    private final CurrencyService currencyService;

    @Value("${movie.randomLimit}")
    private int randomLimit;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom(randomLimit);
    }

    @Override
    public List<Movie> getByGenre(int id) {
        return movieDao.getByGenre(id);
    }

    @Override
    public List<Movie> getAll(MovieRequestParam movieRequestParam) {
        return movieDao.getAll(movieRequestParam);
    }

    @Override
    public List<Movie> getByGenre(int id, MovieRequestParam movieRequestParam) {
        return movieDao.getByGenre(id, movieRequestParam);
    }

    @Override
    public Movie getById(int id) {
        Movie movie = movieDao.getById(id);

        movieEnrichmentService.enrich(movie);

        return movie;
    }

    @Override
    public Movie getById(int id, Currency currency) {
        Movie movie = getById(id);

        movie.setPrice(currencyService.convert(movie.getPrice(), currency));

        return movie;
    }

    @Override
    public Movie add(Movie movie, int[] countryIds, int[] genreIds) {
        return movieDao.add(movie, countryIds, genreIds);
    }

    @Override
    public void edit(Movie movie, int[] countryIds, int[] genreIds) {
        movieDao.edit(movie, countryIds, genreIds);
    }
}