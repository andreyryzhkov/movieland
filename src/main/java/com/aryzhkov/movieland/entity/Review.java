package com.aryzhkov.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private String comment;
    private User user;
    @JsonIgnore
    private Movie movie;
}