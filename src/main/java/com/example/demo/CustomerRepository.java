package com.example.demo;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    /**
     * Customer interface
     * @T - Customer
     * @U - Integer
     */
    List<Customer> findAll();
    Customer findById(Integer id); //read specific customer
    Customer findByName(String name); //read specific customer

    List<Customer> getCustomers(int limit, int offset);

    int addCustomer(Customer object);
    int updateCustomer(Customer object);

    //return country with most customers
    String findCountryWithMostCustomers();

    //return customer with highest total
    String findCustomerWithHighestTotal();

}
