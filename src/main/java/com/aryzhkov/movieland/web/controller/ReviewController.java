package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.Review;
import com.aryzhkov.movieland.entity.User;
import com.aryzhkov.movieland.security.SecurityService;
import com.aryzhkov.movieland.security.util.Session;
import com.aryzhkov.movieland.service.ReviewService;
import com.aryzhkov.movieland.web.dto.ReviewDto;
import com.aryzhkov.movieland.web.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final SecurityService securityService;

    @PostMapping(path = "/review")
    public void addReview(@RequestHeader(value = "token") String token,
                          @RequestBody ReviewDto reviewDto) {
        Optional<Session> session = securityService.getSession(token);
        if (session.isPresent()) {
            User user = session.get().getUser();
            if (securityService.isAuthorized(user)) {
                Movie movie = new Movie();
                movie.setId(reviewDto.getMovieId());

                Review review = new Review();
                review.setUser(user);
                review.setMovie(movie);
                review.setComment(reviewDto.getText());

                reviewService.addReview(review);
            }
        }
    }
}
