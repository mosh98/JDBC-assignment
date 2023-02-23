package com.example.demo.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository <T, U> {
    /**
     * Customer interface
     * @T - Customer
     * @U - Integer, Primary Key
     */
    List<T> findAll();
    T findById(U id); //read specific customer
    int addCustomer(T object) throws SQLException;
    int updateCustomer(T object);

    String findCountryWithMostCustomers();
}