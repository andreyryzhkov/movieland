package com.aryzhkov.movieland.dao.cache;

import com.aryzhkov.movieland.dao.CurrencyDao;
import com.aryzhkov.movieland.dao.util.ExchangeCurrency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CacheCurrencyDao implements CurrencyDao {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private volatile Map<String, Double> cacheCurrencyRate;

    @PostConstruct
    @Scheduled(cron = "${cache.currency.cron}")
    public void refresh() throws IOException {

        List<ExchangeCurrency> exchangeCurrencyList = OBJECT_MAPPER.readValue(new URL(URL), new TypeReference<List<ExchangeCurrency>>() {
        });

        cacheCurrencyRate = exchangeCurrencyList.stream()
                .collect(Collectors.toMap(ExchangeCurrency::getCc, ExchangeCurrency::getRate));
    }

    @Override
    public Map<String, Double> getAll() {
        return new HashMap<>(cacheCurrencyRate);
    }
}