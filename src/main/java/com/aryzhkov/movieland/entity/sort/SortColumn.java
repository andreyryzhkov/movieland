package com.aryzhkov.movieland.entity.sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortColumn {
    private String columnName;
    private SortOrder order;

    @Override
    public String toString() {
        return columnName + ' ' + order;
    }
}
