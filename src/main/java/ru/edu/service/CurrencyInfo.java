package ru.edu.service;

public class CurrencyInfo {

    private String currencyName;
    private String baseCurrencyName;
    private double value;
    private String requestTime;

    public static Builder builder() {
        return new Builder();
    }

    public CurrencyInfo() {
    }

    public static class Builder {

        private CurrencyInfo info = new CurrencyInfo();

        public Builder setCurrencyName(String currencyName) {
            info.currencyName = currencyName;
            return this;
        }

        public Builder setBaseCurrency(String baseCurrency) {
            info.baseCurrencyName = baseCurrency;
            return this;
        }

        public Builder setValue(double value) {
            info.value = value;
            return this;
        }

        public Builder setRequestTime(String requestTime) {
            info.requestTime = requestTime;
            return this;
        }

        public CurrencyInfo build() {
            return info;
        }
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getBaseCurrencyName() {
        return baseCurrencyName;
    }

    public double getValue() {
        return value;
    }

    public String getRequestTime() {
        return requestTime;
    }

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "currencyName='" + currencyName + '\'' +
                ", baseCurrencyName='" + baseCurrencyName + '\'' +
                ", value=" + value +
                ", requestTime='" + requestTime + '\'' +
                '}';
    }
}