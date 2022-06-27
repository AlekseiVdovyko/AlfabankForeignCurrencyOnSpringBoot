package ru.edu.service;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class CompareValue {

    private double pastValueCurrency;
    private double todayValueCurrency;
    private String appKey;

    public String compareValue(double pastValueCurrency, double todayValueCurrency) {

        String gifHttp = "";

        try {
            if (todayValueCurrency >= pastValueCurrency) {

                System.out.println("Курс вырос. Вы увеличили свой капитал");
                String url = "https://api.giphy.com/v1/gifs/search?api_key=" + appKey + "&limit=1&q=rich";
                RestTemplate restTemplate = new RestTemplate();
                String answerServer = restTemplate.getForObject(url, String.class);
                gifHttp = JsonPath.parse(answerServer).read("$.data[0].url");

                System.out.println("Rich " + "gifHttp = " + gifHttp);
            }
            else {
                System.out.println("Курс снизился. Вы потеряли деньги");
                String url = "https://api.giphy.com/v1/gifs/search?api_key=" + appKey + "&limit=1&q=broke";
                RestTemplate restTemplate = new RestTemplate();
                String answerServer = restTemplate.getForObject(url, String.class);
                gifHttp = JsonPath.parse(answerServer).read("$.data[0].url");

                System.out.println("Broke " + "gifHttp = " + gifHttp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gifHttp;
    }

    public void setPastValueCurrency(double pastValueCurrency) {
        this.pastValueCurrency = pastValueCurrency;
    }

    public void setTodayValueCurrency(double todayValueCurrency) {
        this.todayValueCurrency = todayValueCurrency;
    }

    @Value("${compareValue.appKey}")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public String toString() {
        return "CompareValue{" +
                "pastValueCurrency=" + pastValueCurrency +
                ", todayValueCurrency=" + todayValueCurrency +
                '}';
    }
}
