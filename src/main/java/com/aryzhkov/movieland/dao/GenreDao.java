package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    List<Genre> getById(int id);
}
