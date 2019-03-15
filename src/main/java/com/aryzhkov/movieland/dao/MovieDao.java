package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    List<Movie> getRandom();
}
