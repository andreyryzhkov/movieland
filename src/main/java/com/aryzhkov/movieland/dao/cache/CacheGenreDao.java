package com.aryzhkov.movieland.dao.cache;

import com.aryzhkov.movieland.dao.GenreDao;
import com.aryzhkov.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
@Primary
public class CacheGenreDao implements GenreDao {

    private final GenreDao genreDao;

    private volatile List<Genre> cacheGenres;

    @PostConstruct
    @Scheduled(fixedDelayString = "${cache.fixedDelay}")
    public void refresh() {
        log.info("Refresh genre cache");
        cacheGenres = genreDao.getAll();
    }

    @Override
    public List<Genre> getAll() {
        return new ArrayList<>(cacheGenres);
    }
}