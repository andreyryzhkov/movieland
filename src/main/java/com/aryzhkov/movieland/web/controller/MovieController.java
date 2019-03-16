package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.web.dto.MovieDto;
import com.aryzhkov.movieland.service.MovieService;
import com.aryzhkov.movieland.service.util.MovieDtoConverterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    private final MovieDtoConverterImpl movieDTOConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getAll() {
        return movieDTOConverter.convert(movieService.getAll());
    }

    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getRandom() {
        return movieDTOConverter.convert(movieService.getRandom());
    }

    @GetMapping(path = "/genre/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getByGenre(@PathVariable int id) {
        return movieDTOConverter.convert(movieService.getByGenre(id));
    }
}