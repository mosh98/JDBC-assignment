package com.example.demo;

import java.util.List;

public interface CrudRepository <T, U> {
    /**
     * Customer interface
     * @T - Customer
     * @U - Integer
     */
    List<T> findAll();
    T findById(U id); //read specific customer

//    T findByName(T name); //read specific customer

    List<T> getCustomers(int limit, int offset);

    int addCustomer(T object);
    int updateCustomer(T object);

    //return country with most customers
    String findCountryWithMostCustomers();

    //return customer with highest total
    String findCustomerWithHighestTotal();
}
