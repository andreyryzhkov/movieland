package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.ReviewDao;
import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    @Override
    public void enrich(Movie movie) {
        movie.setReviews(reviewDao.getByMovieId(movie.getId()));
    }
}