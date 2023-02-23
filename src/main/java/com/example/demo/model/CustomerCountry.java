package com.example.demo.model;

public class CustomerCountry {
    /**
     * This class is used to store & display the customer's country in the database.
     */

    String countryName;

    public CustomerCountry(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }


    @Override
    public String toString() {
        return "CustomerCountry{" +
                "countryName='" + countryName + '\'' +
                '}';
    }
}
