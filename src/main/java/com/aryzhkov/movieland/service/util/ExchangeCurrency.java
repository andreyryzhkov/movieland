package com.aryzhkov.movieland.service.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties({"r030","txt","exchangedate"})
public class ExchangeCurrency {

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("cc")
    private String currency;
}
