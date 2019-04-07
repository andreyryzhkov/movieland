package com.aryzhkov.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Movie {

    private int id;
    private String nameNative;
    private String nameRussian;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;

    public Movie(Movie movie) {
        id = movie.getId();
        nameRussian = movie.getNameRussian();
        nameNative = movie.getNameNative();
        yearOfRelease = movie.getYearOfRelease();
        description = movie.getDescription();
        rating = movie.getRating();
        price = movie.getPrice();
        picturePath = movie.getPicturePath();
        countries = movie.getCountries();
        genres = movie.getGenres();
        reviews = movie.getReviews();
    }
}
