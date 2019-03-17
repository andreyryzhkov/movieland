package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.entity.Genre;
import com.aryzhkov.movieland.service.GenreService;
import com.aryzhkov.movieland.service.cache.GenreCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreCacheService genreCacheService;

    @Override
    public List<Genre> getAll() {
        return genreCacheService.getAll();
    }
}