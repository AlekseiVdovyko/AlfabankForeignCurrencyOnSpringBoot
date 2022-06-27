package ru.edu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.edu.service.CompareValue;
import ru.edu.service.CurrencyCache;
import ru.edu.service.CurrencyProvider;

@Configuration
@PropertySource("classpath:app.properties")
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CurrencyProvider currencyProvider() {
        return new CurrencyProvider(restTemplate());
    }

    @Bean
    public CurrencyCache currencyCache() {
        return new CurrencyCache();
    }

    @Bean
    public CompareValue compareValue() {
        return new CompareValue();
    }
}
