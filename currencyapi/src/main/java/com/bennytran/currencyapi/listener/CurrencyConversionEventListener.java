package com.bennytran.currencyapi.listener;


import com.bennytran.currencyapi.domain.CurrencyConversion;
import com.bennytran.currencyapi.domain.CurrencyExchange;
import com.bennytran.currencyapi.domain.Rate;
import com.bennytran.currencyapi.event.CurrencyConversionEvent;
import com.bennytran.currencyapi.repository.RateRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CurrencyConversionEventListener implements ApplicationListener<CurrencyConversionEvent> {

    private static final String DASH_LINE = "=========================================";
    private static final String NEXT_LINE = "\n";
    private static final Logger log = LoggerFactory.getLogger(CurrencyConversionEventListener.class);

    @Override
    public void onApplicationEvent(CurrencyConversionEvent event) {
        Object obj = event.getSource();
        StringBuilder str = new StringBuilder(NEXT_LINE);
        str.append(DASH_LINE);
        str.append(NEXT_LINE);
        str.append(" Class:" + obj.getClass().getSimpleName());
        str.append(NEXT_LINE);
        str.append("Message:" + event.getMessage());
        str.append(NEXT_LINE);
        str.append(" Value:" + event.getConversion());
        str.append(NEXT_LINE);
        str.append(DASH_LINE);
        log.error(str.toString());
    }

    public CurrencyConversion convertFromTo(String base, String code, Float amount) {
        Rate baseRate = new Rate(CurrencyExchange.BASE_CODE, 1.0F, new Date());
        Rate codeRate = new Rate(CurrencyExchange.BASE_CODE, 1.0F, new Date());
//        RateRepository repository = new RateRepository();
        if (!CurrencyExchange.BASE_CODE.equals(base.toUpperCase())) {
            baseRate = repository.findByDateAndCode(new Date(), base);
        }
        if (!CurrencyExchange.BASE_CODE.equals(code.toUpperCase())) {
            codeRate = repository.findByDateAndCode(new Date(), code);
        }

        if (null == codeRate || null == baseRate) {
            throw new BadCodeRuntimeException("Bad Code Base, unknown code: " + base.toUpperCase(), new CurrencyConversion(base, code, amount, -1F));
        }

        return new CurrencyConversion(base, code, amount, (codeRate.getRate() / baseRate.getRate()) * amount);
    }
}
