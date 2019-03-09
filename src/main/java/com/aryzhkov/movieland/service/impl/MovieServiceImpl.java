package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.dto.MovieDTO;
import com.aryzhkov.movieland.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}