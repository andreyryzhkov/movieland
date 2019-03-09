package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    public List<Movie> getAll();
}
