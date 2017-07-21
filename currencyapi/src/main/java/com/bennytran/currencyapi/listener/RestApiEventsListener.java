package com.bennytran.currencyapi.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RestApiEventsListener implements ApplicationListener<ApplicationEvent> {

    private static final String LATEST = "/currency/latest";

    @Autowired
    private CounterService counterService;

    @Log(printParamsValues=true)
    public void onApplicationEvent(ApplicationEvent event) {
        if (event )
    }
}
