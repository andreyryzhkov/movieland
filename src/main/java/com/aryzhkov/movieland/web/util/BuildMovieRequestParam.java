package com.aryzhkov.movieland.web.util;

public class BuildMovieRequestParam {

    public MovieRequestParam getMovieRequestParam(SortOrder ratingOrder, SortOrder priceOrder) {
        MovieRequestParam movieRequestParam = new MovieRequestParam();

        if (SortOrder.DESC.equals(ratingOrder)) {
            movieRequestParam.setField("rating");
            movieRequestParam.setOrder(ratingOrder);
        }

        if (SortOrder.ASC.equals(priceOrder) || SortOrder.DESC.equals(priceOrder)) {
            movieRequestParam.setField("price");
            movieRequestParam.setOrder(priceOrder);
        }
        return movieRequestParam;
    }

    public boolean isValid(MovieRequestParam movieRequestParam) {
        return movieRequestParam.getField() != null && movieRequestParam.getOrder() != null;
    }
}