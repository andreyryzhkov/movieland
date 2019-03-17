package com.aryzhkov.movieland.dao.jdbc.util;

import com.aryzhkov.movieland.entity.sort.SortColumn;

import java.util.List;
import java.util.StringJoiner;

public class QueryConstructor {

    private static final String ORDER_BY_CLAUSE = " ORDER BY ";

    public static String getQuery(String query, List<SortColumn> sortColumns) {
        return query + ORDER_BY_CLAUSE + getOrderByClause(sortColumns);
    }

    private static String getOrderByClause(List<SortColumn> sortColumns) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (SortColumn sortColumn : sortColumns) {
            stringJoiner.add(sortColumn.toString());
        }
        return stringJoiner.toString();
    }
}
