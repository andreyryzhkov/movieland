package com.aryzhkov.movieland.dao;

import com.aryzhkov.movieland.entity.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getByMovieId(int id);
}
