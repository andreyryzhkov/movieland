package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.jdbc.JdbcGenreDao;
import com.aryzhkov.movieland.entity.Genre;
import com.aryzhkov.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final JdbcGenreDao jdbcGenreDao;

    @Autowired
    public GenreServiceImpl(JdbcGenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }

    @Override
    public List<Genre> getAll() {
        return jdbcGenreDao.getAll();
    }
}
