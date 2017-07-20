package com.bennytran.currencyapi.controller;

import com.bennytran.currencyapi.domain.CurrencyConversion;
import com.bennytran.currencyapi.domain.CurrencyExchange;
import com.bennytran.currencyapi.domain.Rate;
import com.bennytran.currencyapi.service.CurrencyConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    CurrencyConversionService service;

    @RequestMapping("/latest")
    public ResponseEntity<CurrencyExchange> getLatest(@RequestParam(name="base", defaultValue=CurrencyExchange.BASE_CODE) String base)
        throws Exception {
        return new ResponseEntity<CurrencyExchange>(new CurrencyExchange(base, new SimpleDateFormat("yyyy-MM-dd").format(
                new Date()), service.calculateByCode(base, new Date())), HttpStatus.OK);
    }

    @RequestMapping("/{date}")
    public ResponseEntity<CurrencyExchange> getByDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date, @RequestParam(name="base",
            defaultValue = CurrencyExchange.BASE_CODE) String base) throws Exception {
        return new ResponseEntity<CurrencyExchange>(new CurrencyExchange(base, new SimpleDateFormat("yyyy-MM-dd").format(date), service.calculateByCode(base, date)), HttpStatus.OK);
    }

    /**
     * PathVariable annotation takes variables from request mapping
     * @param amount
     * @param base
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping("/{amount}/{base}/to/{code}")
    public ResponseEntity<CurrencyConversion> conversion (@PathVariable("amount") Float amount, @PathVariable("base") String base,
                                                          @PathVariable("code") String code) throws Exception {
        CurrencyConversion conversionResult = service.convertFromTo(base, code, amount);
        return new ResponseEntity<CurrencyConversion>(conversionResult, HttpStatus.OK);
    }

    @RequestMapping(path="/new", method= {RequestMethod.POST})
    public ResponseEntity<CurrencyExchange> addNewRates(@RequestBody CurrencyExchange currencyExchange) throws Exception {
        try {
            final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currencyExchange.getDate());
            final Rate[] rates = currencyExchange.getRates();
            service.saveRates(rates, date);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
        return new ResponseEntity<CurrencyExchange>(HttpStatus.CREATED);
    }

}
