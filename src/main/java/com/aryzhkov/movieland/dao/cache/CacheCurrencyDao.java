package com.aryzhkov.movieland.dao.cache;

import com.aryzhkov.movieland.dao.CurrencyDao;
import com.aryzhkov.movieland.dao.util.ExchangeCurrency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CacheCurrencyDao implements CurrencyDao {

    @Value("${cache.currency.url}")
    private String URL;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ConcurrentHashMap<String, Double> cacheCurrency = new ConcurrentHashMap<>();

    @PostConstruct
    @Scheduled(cron = "${cache.currency.cron}")
    public void refresh() throws IOException {

        List<ExchangeCurrency> exchangeCurrencyList = OBJECT_MAPPER.readValue(new URL(URL), new TypeReference<List<ExchangeCurrency>>() {
        });

        for (ExchangeCurrency exchangeCurrency : exchangeCurrencyList) {
            cacheCurrency.put(exchangeCurrency.getCurrency(), exchangeCurrency.getRate());
        }
    }

    @Override
    public Map<String, Double> getAll() {
        return new HashMap<>(cacheCurrency);
    }
}