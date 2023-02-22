package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    /**
     * 4. Return a page of customers from the database. This should take in limit and offset as parameters and make use of the SQL limit and offset keywords to get a subset of the customer data.
     *    The customer model from above should be reused.
     *  5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)
     * 6. Update an existing customer. [DONE]
     * 7. Return the country with the most customers.
     * 8. Return the customer with the highest total.
     */

    private final JdbcTemplate jdbcTemplate;
    private final String url;
    private final String username;
    private final String password;

    @Autowired
    public CustomerRepositoryImpl(
            JdbcTemplate jdbcTemplate, @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.jdbcTemplate = jdbcTemplate;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("company"),
                        result.getString("address"),
                        result.getString("city"),
                        result.getString("state"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("fax"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("company"),
                        result.getString("address"),
                        result.getString("city"),
                        result.getString("state"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("fax"),
                        result.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer findByName(String name) {
        String sql = "SELECT * FROM customer WHERE first_name LIKE ?";
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("company"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("fax"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
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
