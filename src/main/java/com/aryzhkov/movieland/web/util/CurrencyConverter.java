package com.aryzhkov.movieland.web.util;

import java.beans.PropertyEditorSupport;

public class CurrencyConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Currency currency = Currency.getByCurrency(text);
        setValue(currency);
    }
}
