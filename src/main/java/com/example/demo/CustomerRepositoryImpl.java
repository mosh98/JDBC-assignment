package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl extends CustomerRepository {

    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
        @Value("${spring.datasource.url}") String url,
        @Value("${spring.datasource.username}") String username,
        @Value("${spring.datasource.password}") String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Customer> findAll() {
        return super.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public Customer findByName(Customer name) {
        return super.findByName(name);
    }

    @Override
    public int addCustomer(Customer object) {
        return super.addCustomer(object);
    }

    @Override
    public int updateCustomer(Customer object) {
        return super.updateCustomer(object);
    }
}
