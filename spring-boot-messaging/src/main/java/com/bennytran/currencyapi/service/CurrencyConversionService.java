package com.bennytran.currencyapi.service;

import com.bennytran.currencyapi.domain.Rate;
import com.bennytran.currencyapi.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CurrencyConversionService {

    @Autowired
    RateRepository repository;

    public CurrencyConversion convertFromTo(@ToUpper String base, @ToUpper String code, Float amount) throws Exception{
        Rate baseRate = new Rate(CurrencyExchange.BAE_CODE, 1.0F, new Date());
    }
}
