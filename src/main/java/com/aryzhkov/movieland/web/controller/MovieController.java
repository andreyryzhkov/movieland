package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.entity.util.MovieRequestParam;
import com.aryzhkov.movieland.entity.util.SortOrder;
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
    public List<MovieDto> getAll(
            @RequestParam(value = "rating", required = false) SortOrder ratingOrder,
            @RequestParam(value = "price", required = false) SortOrder priceOrder) {

        MovieRequestParam movieRequestParam = new MovieRequestParam();

        if (movieRequestParam.isOrder(ratingOrder, priceOrder)) {
            return movieDTOConverter.convert(movieService.getAll(movieRequestParam));
        }

        return movieDTOConverter.convert(movieService.getAll());
    }

    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getRandom() {
        return movieDTOConverter.convert(movieService.getRandom());
    }

    @GetMapping(path = "/genre/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getByGenre(@PathVariable int id,
                                     @RequestParam(value = "rating", required = false) String ratingOrder,
                                     @RequestParam(value = "price", required = false) String priceOrder) {
        MovieRequestParam movieRequestParam = new MovieRequestParam();
        if (ratingOrder != null)
            return movieDTOConverter.convert(movieService.getByGenre(id, movieRequestParam));
        else if (priceOrder != null)
            return movieDTOConverter.convert(movieService.getByGenre(id, movieRequestParam));

        return movieDTOConverter.convert(movieService.getByGenre(id));
    }
}