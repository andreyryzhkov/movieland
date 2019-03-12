package com.aryzhkov.movieland.service.util;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.web.dto.MovieDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MovieDtoConverterImpl implements MovieDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public MovieDtoConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> convert(List<Movie> movies) {
        Type targetListType = new TypeToken<List<MovieDto>>() {
        }.getType();
        List<MovieDto> movieDtos = modelMapper.map(movies, targetListType);

        return movieDtos;
    }
}