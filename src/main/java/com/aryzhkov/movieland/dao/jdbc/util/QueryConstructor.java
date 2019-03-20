package com.aryzhkov.movieland.dao.jdbc.util;

import com.aryzhkov.movieland.web.util.MovieRequestParam;

public class QueryConstructor {

    private static final String ORDER_BY_CLAUSE = " ORDER BY ";

    public static String Query(String query, MovieRequestParam movieRequestParam) {
        return query + ORDER_BY_CLAUSE + movieRequestParam.getOrderByClause();
    }
}
