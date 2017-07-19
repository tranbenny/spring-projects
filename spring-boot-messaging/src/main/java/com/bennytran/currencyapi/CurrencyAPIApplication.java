package com.bennytran.currencyapi;

import com.bennytran.currencyapi.domain.Rate;
import com.bennytran.currencyapi.repository.RateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class CurrencyAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyAPIApplication.class);
    }

    // Method is executed after the Spring container is ready with all the beans
    @Bean
    public CommandLineRunner data(RateRepository repository) {
        return (args) -> {
            repository.save(new Rate("EUR", 0.88857F, new Date()));
            repository.save(new Rate("JPY", 102.17F, new Date()));
            repository.save(new Rate("MXN", 19.232F, new Date()));
            repository.save(new Rate("GBP", 0.75705F, new Date()));
        }
    }

}
