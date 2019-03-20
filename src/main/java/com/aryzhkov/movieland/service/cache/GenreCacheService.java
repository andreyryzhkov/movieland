package com.aryzhkov.movieland.service.cache;

import com.aryzhkov.movieland.entity.Genre;

import java.util.List;

public interface GenreCacheService {

    void refresh();

    List<Genre> getAll();
}
