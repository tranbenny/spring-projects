package com.bennytran.currencyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyConversionService service;

    @RequestMapping("/latest")
    public ResponseEntity<CurrencyExchange> getLatest(@RequestParam(name="base", defaultValue=CurrencyExchange.BASE_CODE) String base)
        throws Exception {

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

    }

}
