package com.aryzhkov.movieland.web.util;

import java.beans.PropertyEditorSupport;

public class SortOrderConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SortOrder sortOrder = SortOrder.getByOrder(text);
        setValue(sortOrder);
    }
}
