package com.aryzhkov.movieland.web.controller;

import com.aryzhkov.movieland.web.util.BuildMovieRequestParam;
import com.aryzhkov.movieland.web.util.MovieRequestParam;
import com.aryzhkov.movieland.web.util.SortOrder;
import com.aryzhkov.movieland.web.dto.MovieDto;
import com.aryzhkov.movieland.service.MovieService;
import com.aryzhkov.movieland.service.util.MovieDtoConverterImpl;
import com.aryzhkov.movieland.web.util.SortOrderConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
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

        BuildMovieRequestParam buildMovieRequestParam = new BuildMovieRequestParam();
        MovieRequestParam movieRequestParam = buildMovieRequestParam.getMovieRequestParam(ratingOrder, priceOrder);

        if (buildMovieRequestParam.isValid(movieRequestParam)) {
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
                                     @RequestParam(value = "rating", required = false) SortOrder ratingOrder,
                                     @RequestParam(value = "price", required = false) SortOrder priceOrder) {

        BuildMovieRequestParam buildMovieRequestParam = new BuildMovieRequestParam();
        MovieRequestParam movieRequestParam = buildMovieRequestParam.getMovieRequestParam(ratingOrder, priceOrder);

        if (buildMovieRequestParam.isValid(movieRequestParam)) {
            return movieDTOConverter.convert(movieService.getByGenre(id, movieRequestParam));
        }

        return movieDTOConverter.convert(movieService.getByGenre(id));
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortOrder.class, new SortOrderConverter());
    }
}