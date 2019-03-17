package com.aryzhkov.movieland.service.cache;

import com.aryzhkov.movieland.dao.GenreDao;
import com.aryzhkov.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreCacheServiceImpl implements GenreCacheService {

    private final GenreDao genreDao;

    private List<Genre> cacheGenre = new ArrayList<>();

    @Override
    @PostConstruct
    @Scheduled(cron = "${genre.cron.expression}")
    public void refresh() {
        cacheGenre = genreDao.getAll();
    }

    @Override
    public List<Genre> getAll() {
        return cacheGenre;
    }
}