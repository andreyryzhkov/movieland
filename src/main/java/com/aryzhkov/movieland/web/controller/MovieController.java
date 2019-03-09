package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.dto.MovieDTO;
import com.aryzhkov.movieland.service.MovieService;
import com.aryzhkov.movieland.service.util.MovieDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    private final MovieDTOConverter movieDTOConverter;

    @GetMapping(path = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDTO> getAll() {
        return movieDTOConverter.convert(movieService.getAll());
    }
}