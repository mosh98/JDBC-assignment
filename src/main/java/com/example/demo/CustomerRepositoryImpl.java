package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    /**
     * 4. Return a page of customers from the database. This should take in limit and offset as parameters and make use of the SQL limit and offset keywords to get a subset of the customer data. The customer model from above should be reused.
     *  5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)
     * 6. Update an existing customer.
     */

    private final JdbcTemplate jdbcTemplate;
    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
            JdbcTemplate jdbcTemplate, @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password){
        this.jdbcTemplate = jdbcTemplate;
        this.url = url;
        this.username = username;
        this.password = password;
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
