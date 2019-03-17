package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.MovieDao;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.sort.SortColumn;
import com.aryzhkov.movieland.entity.sort.SortOrder;
import com.aryzhkov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public List<Movie> getByGenre(int id) {
        return movieDao.getByGenre(id);
    }

    @Override
    public List<Movie> getAll(String columnName, String order) {
        SortColumn sortColumn = new SortColumn();
        sortColumn.setColumnName(columnName);
        sortColumn.setOrder(SortOrder.getByOrder(order));

        return movieDao.getAll(Arrays.asList(sortColumn));
    }

    @Override
    public List<Movie> getByGenre(int id, String columnName, String order) {
        SortColumn sortColumn = new SortColumn();
        sortColumn.setColumnName(columnName);
        sortColumn.setOrder(SortOrder.getByOrder(order));

        return movieDao.getByGenre(id, Arrays.asList(sortColumn));
    }
}