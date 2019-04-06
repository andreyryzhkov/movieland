package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.Review;
import com.aryzhkov.movieland.service.ReviewService;
import com.aryzhkov.movieland.web.auth.UserHolder;
import com.aryzhkov.movieland.web.auth.annotation.ProtectedBy;
import com.aryzhkov.movieland.web.dto.ReviewDto;
import com.aryzhkov.movieland.web.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ProtectedBy({UserRole.USER, UserRole.ADMIN})
    @PostMapping(path = "/review")
    public void addReview(@RequestBody ReviewDto reviewDto) {

        Movie movie = new Movie();
        movie.setId(reviewDto.getMovieId());

        Review review = new Review();
        review.setUser(UserHolder.getCurrentUser());
        review.setMovie(movie);
        review.setComment(reviewDto.getText());

        reviewService.addReview(review);
    }
}
