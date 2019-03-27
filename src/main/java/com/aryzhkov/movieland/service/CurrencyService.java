package com.aryzhkov.movieland.service;

import com.aryzhkov.movieland.web.util.Currency;

public interface CurrencyService {

    double getRate(Currency currency);

    double convert(double price, Currency currency);
}
