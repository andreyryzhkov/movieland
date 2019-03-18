package com.aryzhkov.movieland.entity.util;

import lombok.*;

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

    public boolean isOrder(SortOrder ratingOrder, SortOrder priceOrder) {
        if (SortOrder.DESC.equals(ratingOrder)) {
            this.field = "rating";
            this.order = ratingOrder;
            return true;
        }
        if (SortOrder.ASC.equals(priceOrder) || SortOrder.DESC.equals(priceOrder)) {
            this.field = "price";
            this.order = priceOrder;
            return true;
        }
        return false;
    }
}
