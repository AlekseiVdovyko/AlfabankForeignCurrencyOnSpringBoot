package ru.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyCache {

    private final Map<String, CurrencyInfo> cache = new HashMap<>();
    private CurrencyProvider currencyProvider;

    public CurrencyCache() {
    }

    public CurrencyInfo getCurrencyInfo(String requestTime) {

        if (!cache.containsKey(requestTime)) {
            cache.put(requestTime, currencyProvider.get(requestTime));
        }
        return cache.get(requestTime);
    }

    public Collection<CurrencyInfo> getAll() {
        return cache.values();
    }

    public void removeCurrencyInfo(String requestTime) {
        cache.remove(requestTime);
        System.out.println("Success remove request Time = " + requestTime);

    }

    public Map<String, CurrencyInfo> setCache(String requestTime) {
        cache.put(currencyProvider.get(requestTime).getRequestTime(), currencyProvider.get(requestTime));
        return cache;
    }

    @Autowired
    public void setCurrencyProvider(CurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;
    }
}
