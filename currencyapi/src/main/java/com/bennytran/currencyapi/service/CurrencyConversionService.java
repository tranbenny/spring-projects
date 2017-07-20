package com.bennytran.currencyapi.service;

import com.bennytran.currencyapi.domain.CurrencyConversion;
import com.bennytran.currencyapi.domain.CurrencyExchange;
import com.bennytran.currencyapi.domain.Rate;
import com.bennytran.currencyapi.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CurrencyConversionService {

    @Autowired
    RateRepository repository;

    public CurrencyConversion convertFromTo(String base, String code, Float amount) throws Exception{

        Rate baseRate = new Rate(CurrencyExchange.BASE_CODE, 1.0F, new Date());
        Rate codeRate = new Rate(CurrencyExchange.BASE_CODE, 1.0F, new Date());

        if (!CurrencyExchange.BASE_CODE.equals(base.toUpperCase())) {
            baseRate = repository.findByDateAndCode(new Date(), base.toUpperCase());
        }

        if (!CurrencyExchange.BASE_CODE.equals(code.toUpperCase())) {
            codeRate = repository.findByDateAndCode(new Date(), code.toUpperCase());
        }

        return new CurrencyConversion(base.toUpperCase(), code.toUpperCase(), amount, (codeRate.getRate() / baseRate.getRate()) * amount);
    }

    public Rate[] calculateByCode(String code, Date date) throws Exception {
        List<Rate> rates = repository.findByDate(date);
        if (code.equals(CurrencyExchange.BASE_CODE)) {
            return rates.toArray(new Rate[0]);
        }

        Rate baseRate = rates.stream().filter(rate -> rate.getCode().equals(code.toUpperCase())).findFirst().orElse(null);
        if (baseRate == null) {
            throw new Exception("Bad Base Code");
        }

        return Stream.concat(rates.stream()
            .filter(n -> !n.getCode().equals(code.toUpperCase()))
            .map(n -> new Rate(n.getCode(), n.getRate() / baseRate.getRate(), date)), Stream.of(new Rate(CurrencyExchange.BASE_CODE, 1/ baseRate.getRate(), date)))
            .toArray(size -> new Rate[size]);
    }

    public void saveRates(Rate[] rates, Date date) {
        Arrays.stream(rates).forEach(rate -> repository.save(new Rate(rate.getCode(), rate.getRate(), date)));
    }
}
