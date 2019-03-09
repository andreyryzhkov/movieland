package com.aryzhkov.movieland.service.util;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.entity.dto.MovieDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MovieDTOConverter {

    private ModelMapper modelMapper;

    @Autowired
    public MovieDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> convert(List<Movie> movies) {
        Type targetListType = new TypeToken<List<MovieDTO>>() {
        }.getType();
        List<MovieDTO> movieDTOS = modelMapper.map(movies, targetListType);

        return movieDTOS;
    }
}