package com.aryzhkov.movieland.service.impl;

import com.aryzhkov.movieland.dao.CurrencyDao;
import com.aryzhkov.movieland.service.CurrencyService;
import com.aryzhkov.movieland.web.util.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDao currencyDao;

    @Override
    public double getRate(Currency currency) {
        Map<String, Double> map = currencyDao.getAll();
        return map.get(currency.getCurrency());
    }

    @Override
    public double convert(double price, Currency currency) {
        return price / getRate(currency);
    }
}