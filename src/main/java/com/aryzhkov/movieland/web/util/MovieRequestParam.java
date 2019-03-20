package com.aryzhkov.movieland.web.util;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestParam {
    private String field;
    private SortOrder order;

    public String getOrderByClause() {
        return field + " " + order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieRequestParam that = (MovieRequestParam) o;
        return Objects.equals(field, that.field) &&
                order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, order);
    }
}
