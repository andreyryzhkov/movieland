package com.aryzhkov.movieland.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {

    private int id;
    private String nameNative;
    private String nameRussian;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String currency;
    private String picturePath;
}