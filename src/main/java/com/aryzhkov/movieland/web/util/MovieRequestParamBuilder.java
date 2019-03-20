package com.aryzhkov.movieland.web.util;

public class MovieRequestParamBuilder {

    public static MovieRequestParam getMovieRequestParam(SortOrder ratingOrder, SortOrder priceOrder) {

        if (SortOrder.DESC.equals(ratingOrder)) {
            return new MovieRequestParam("rating", ratingOrder);
        }

        if (SortOrder.ASC.equals(priceOrder) || SortOrder.DESC.equals(priceOrder)) {
            return new MovieRequestParam("price", priceOrder);
        }
        return null;
    }
}