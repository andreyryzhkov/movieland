package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.sort.SortColumn;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    List<Movie> getRandom(int randomLimit);

    List<Movie> getByGenre(int id);

    List<Movie> getAll(List<SortColumn> sortColumns);

    List<Movie> getByGenre(int id, List<SortColumn> sortColumns);
}
