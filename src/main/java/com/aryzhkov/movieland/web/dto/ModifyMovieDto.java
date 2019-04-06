package com.aryzhkov.movieland.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyMovieDto {
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double price;
    private String picturePath;
    int[] countries;
    int[] genres;
}
