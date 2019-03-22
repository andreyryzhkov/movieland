package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.GenreDao;
import com.aryzhkov.movieland.entity.Genre;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void enrich(Movie movie) {
        movie.setGenres(genreDao.getById(movie.getId()));
    }
}