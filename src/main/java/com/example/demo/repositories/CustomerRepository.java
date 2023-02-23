package com.example.demo.repositories;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomersFavoriteGenre;
import com.example.demo.model.HighestSpender;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    /**
     * Customer interface
     *
     * @T - Customer
     * @U - Integer, Primary Key
     */
    List<Customer> findAll();

    Customer findById(Integer id); //read specific customer

    Customer findByName(String name); //read specific customer

    // number 4
    List<Customer> findCustomersPage(int limit, int offset);

    int addCustomer(Customer object) throws SQLException;

    int updateCustomer(Customer object);

    String findCountryWithMostCustomers();

    HighestSpender findHighestSpender();

    CustomersFavoriteGenre mostPopGenre(int id);
}