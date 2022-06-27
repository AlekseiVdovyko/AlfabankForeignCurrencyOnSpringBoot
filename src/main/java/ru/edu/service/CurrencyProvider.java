package ru.edu.service;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class CurrencyProvider {

    private RestTemplate restTemplate;
    private String appKey;
    private String currencyName;

    public CurrencyProvider() {
    }

    public CurrencyProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${currencyProvider.appKey}")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Value("${currencyProvider.currencyName}")
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public CurrencyInfo get(String requestTime) {
        String baseCurrencyName = "";
        double value = 0.0;

        try {
            String url = "https://openexchangerates.org/api/historical/" + requestTime +".json?app_id=" + appKey;

            RestTemplate restTemplate = new RestTemplate();
            String answerServer = restTemplate.getForObject(url, String.class);

            baseCurrencyName = JsonPath.parse(answerServer).read("$.base");

            value = JsonPath.parse(answerServer).read("$.rates." + currencyName);

        } catch (Exception ex) {
            return null;
        }

        CurrencyInfo currencyInfo = CurrencyInfo.builder()
                .setCurrencyName(currencyName)
                .setBaseCurrency(baseCurrencyName)
                .setValue(value)
                .setRequestTime(requestTime)
                .build();

        return currencyInfo;
    }

}
