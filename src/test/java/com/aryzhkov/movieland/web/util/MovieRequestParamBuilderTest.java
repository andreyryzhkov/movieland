package com.aryzhkov.movieland.web.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieRequestParamBuilderTest {

    @Test
    public void getMovieRequestParam() {
        MovieRequestParam expectedMovieRequestParam1 = new MovieRequestParam("rating", SortOrder.DESC);
        MovieRequestParam actualMovieRequestParam1 = MovieRequestParamBuilder.getMovieRequestParam(SortOrder.DESC, SortOrder.ASC);

        assertEquals(expectedMovieRequestParam1, actualMovieRequestParam1);

        MovieRequestParam expectedMovieRequestParam2 = new MovieRequestParam("price", SortOrder.ASC);
        MovieRequestParam actualMovieRequestParam2 = MovieRequestParamBuilder.getMovieRequestParam(SortOrder.ASC, SortOrder.ASC);

        assertEquals(expectedMovieRequestParam2, actualMovieRequestParam2);
    }
}