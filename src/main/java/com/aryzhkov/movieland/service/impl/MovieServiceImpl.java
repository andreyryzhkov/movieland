package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    @Value("${movie.randomLimit}")
    private int randomLimit;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getRandom() {
        return movieDao.getRandom(randomLimit);
    }
}