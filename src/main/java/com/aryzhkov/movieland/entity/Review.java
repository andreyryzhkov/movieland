package com.aryzhkov.movieland.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private int id;
    private String comment;
    private double rating;
    private User user;
}