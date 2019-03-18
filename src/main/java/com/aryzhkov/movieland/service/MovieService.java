package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getRandom();

    List<Movie> getByGenre(int id);
}
