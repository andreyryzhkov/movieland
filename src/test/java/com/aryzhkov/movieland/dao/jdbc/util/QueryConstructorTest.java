package com.aryzhkov.movieland.dao.jdbc.util;

import com.aryzhkov.movieland.web.util.MovieRequestParam;
import com.aryzhkov.movieland.web.util.SortOrder;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryConstructorTest {

    @Test
    public void testQuery() {
        String query = "SELECT movie_id, native_name, russian_name, rating, FROM movieland.movie";
        MovieRequestParam movieRequestParam = new MovieRequestParam("rating", SortOrder.DESC);

        String expectedQuery = "SELECT movie_id, native_name, russian_name, rating, FROM movieland.movie ORDER BY rating DESC";
        String actualQuery = QueryConstructor.Query(query, movieRequestParam);

        assertEquals(expectedQuery, actualQuery);
    }
}