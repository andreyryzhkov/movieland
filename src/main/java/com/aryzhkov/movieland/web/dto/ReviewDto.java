package com.aryzhkov.movieland.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private int movieId;
    private String text;
}
