package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.util.MovieRequestParam;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    List<Movie> getRandom(int randomLimit);

    List<Movie> getByGenre(int id);

    List<Movie> getAll(MovieRequestParam movieRequestParam);

    List<Movie> getByGenre(int id, MovieRequestParam movieRequestParam);
}
