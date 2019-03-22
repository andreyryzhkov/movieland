package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Genre;
import com.aryzhkov.movieland.entity.Movie;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    void enrich(Movie movie);
}
