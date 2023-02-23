package com.example.demo.model;

public class CustomerCountry {

    String countryName;

    public CustomerCountry(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "CustomerCountry{" +
                "countryName='" + countryName + '\'' +
                '}';
    }
}
