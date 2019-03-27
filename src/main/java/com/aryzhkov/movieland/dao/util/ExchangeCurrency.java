package com.aryzhkov.movieland.dao.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExchangeCurrency {

    private int r030;
    private String txt;
    private double rate;
    private String cc;
    private String exchangedate;

    public String getCurrency() {
        return cc;
    }
}
