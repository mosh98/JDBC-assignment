package com.example.demo;

import java.util.List;

public class CustomerRepository implements CrudRepository<Customer, Integer> {

    public CustomerRepository() {
        super();
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer findByName(Customer name) {
        return null;
    }

    @Override
    public int addCustomer(Customer object) {
        return 0;
    }

    @Override
    public int updateCustomer(Customer object) {



        return 0;
    }
}
