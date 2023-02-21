package com.example.demo;

public class Customer {
    private int id;
    private String first_name;

    private String last_name;

    private String company;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postal_code;

    private String phone;

    private String fax;

    private String email;


    public Customer(int id, String first_name, String last_name, String company, String address, String city, String state, String country, String postal_code, String phone, String fax, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal_code = postal_code;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }
}