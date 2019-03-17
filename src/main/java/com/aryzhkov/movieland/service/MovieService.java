package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getRandom();

    List<Movie> getByGenre(int id);

    List<Movie> getAll(String columnName, String order);

    List<Movie> getByGenre(int id, String columnName, String order);
}
