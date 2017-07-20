package com.bennytran.currencyapi.domain;

import java.util.Date;

/**
 * Created by bennytran on 7/19/17.
 */
public class CurrencyExchange {

    public static final String BASE_CODE = "EUR";
    private String base;
    private String format;
    private Rate[] rates;
    private String date;

    public CurrencyExchange(String base, String format, Rate[] rates) {
        this.base = base;
        this.format = format;
        this.rates = rates;
    }

    public static String getBaseCode() {
        return BASE_CODE;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Rate[] getRates() {
        return rates;
    }

    public void setRates(Rate[] rates) {
        this.rates = rates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}