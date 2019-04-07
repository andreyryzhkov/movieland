package com.aryzhkov.movieland.service.util;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.dto.ModifyMovieDto;
import com.aryzhkov.movieland.web.dto.MovieDto;

import java.util.List;

public interface MovieDtoConverter {

    List<MovieDto> convert(List<Movie> movies);

    Movie convert(ModifyMovieDto modifyMovieDto);
}
