package com.aryzhkov.movieland.entity.sort;

public enum SortOrder {
    ASC("ASC"),
    DESC("DESC");

    private final String order;

    SortOrder(String order) {
        this.order = order;
    }

    public static SortOrder getByOrder(String order) {
        SortOrder[] sortOrders = SortOrder.values();
        for (SortOrder sortOrder : sortOrders) {
            if (sortOrder.getOrder().equalsIgnoreCase(order)) {
                return sortOrder;
            }
        }
        throw new IllegalArgumentException("No type for order " + order);
    }

    public String getOrder() {
        return order;
    }
}