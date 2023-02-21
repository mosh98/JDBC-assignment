package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    /**
     * 4. Return a page of customers from the database. This should take in limit and offset as parameters and make use of the SQL limit and offset keywords to get a subset of the customer data.
     *    The customer model from above should be reused.
     *  5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)
     * 6. Update an existing customer. [DONE]
     * 7. Return the country with the most customers.
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

    //number 5
    @Override
    public int addCustomer(Customer object) {
        //Make an sql string to insert into the database for customer
        String sql = "INSERT INTO customer (first_name, last_name, company, address, city, state, country,  postal_code, phone,fax, email) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, object.getFirst_name(),
                object.getLast_name(),
                object.getCompany(),
                object.getAddress(),
                object.getCity(),
                object.getState(),
                object.getCountry(),
                object.getPostal_code(),
                object.getPhone(),
                object.getFax(),
                object.getEmail());
    }

    //number 6


    @Override
    public int updateCustomer(Customer object) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, company = ?, address = ?, city = ?, state = ?, country = ?,  postal_code = ?, phone = ?, fax = ?, email = ? WHERE customer_id = ?";

        return jdbcTemplate.update(sql, object.getFirst_name(),
                object.getLast_name(),
                object.getCompany(),
                object.getAddress(),
                object.getCity(),
                object.getState(),
                object.getCountry(),
                object.getPostal_code(),
                object.getPhone(),
                object.getFax(),
                object.getEmail(),
                object.getId());
    }

    //number 7
    @Override
    public String findCountryWithMostCustomers() {
        String sql = "SELECT country, COUNT(*) AS count FROM customer GROUP BY country ORDER BY count DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, String.class);
    }


    //number 8
    @Override
    public String findCustomerWithHighestTotal() {
        String sql = "SELECT c.first_name, MAX(i.total) AS total_spent\n" +
                "FROM customer c\n" +
                "         JOIN invoice i ON c.customer_id = i.customer_id\n" +
                "GROUP BY c.customer_id\n" +
                "ORDER BY total_spent DESC\n" +
                "LIMIT 1";
        return jdbcTemplate.queryForObject(sql, String.class);
    }


}
