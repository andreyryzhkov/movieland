package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.CountryDao;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Override
    public void enrich(Movie movie) {
        movie.setCountries(countryDao.getByMovieId(movie.getId()));
    }
}
