package com.aryzhkov.movieland.web.util;

public enum Currency {
    EUR("EUR"),
    USD("USD");

    private final String currency;

    public static Currency getByCurrency(String currency) {
        Currency[] currencies = Currency.values();
        for (Currency ccy : currencies) {
            if (ccy.getCurrency().equalsIgnoreCase(currency)) {
                return ccy;
            }
        }
        throw new IllegalArgumentException("No such currency " + currency);
    }

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
