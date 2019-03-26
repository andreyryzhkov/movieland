package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.util.Currency;
import com.aryzhkov.movieland.web.util.MovieRequestParam;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getRandom();

    List<Movie> getByGenre(int id);

    List<Movie> getAll(MovieRequestParam movieRequestParam);

    List<Movie> getByGenre(int id, MovieRequestParam movieRequestParam);

    Movie getById(int id);

    Movie getById(int id, Currency currency);
}
