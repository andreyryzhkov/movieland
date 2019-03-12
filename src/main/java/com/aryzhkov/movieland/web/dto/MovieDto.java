package com.aryzhkov.movieland.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
    private int id;
    private String nameNative;
    private String nameRussian;
    private String yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}
