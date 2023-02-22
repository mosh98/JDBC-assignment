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
        /**
         * Return all customers from the database.
         * @return List of all customers
         */
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
        /**
         * Return a customer from the database with the given id.
         * @param id The id of the customer to return
         * @return The customer with the given id
         */
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

    //number 4
    @Override
    public List<Customer> getCustomers(int limit, int offset) {
        /**
         * Return a list of customers from the database with the given limit and offset.
         * @param limit The number of customers to return
         * @param offset The number of customers to skip
         * @return A list of customers with the given limit and offset
         */

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer ORDER BY customer_id LIMIT ? OFFSET ?")) {

            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
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
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }


    @Override
    public Customer findByName(String name) {
        /**
         * Return a customer from the database with the given name.
         * @param name The name of the customer to return
         * @return The customer with the given name
         * @throws RuntimeException if there is an error with the database
         */
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
        /**
         * Add a customer to the database.
         * @param object The customer to add
         * @return The number of rows affected
         */
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
        /**
         * Update a customer in the database.
         * @param object The customer to update
         * @return The number of rows affected
         * @throws RuntimeException if there is an error with the database
         * @throws IllegalArgumentException if the customer does not exist
         * @throws IllegalArgumentException if the customer id is null
         */

        String sql = "UPDATE customer SET first_name = ?, last_name = ?, company = ?, address = ?, city = ?, state = ?, country = ?,  postal_code = ?, phone = ?, fax = ?, email = ? WHERE customer_id = ?";


        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getFirst_name());
            statement.setString(2, object.getLast_name());
            statement.setString(3, object.getCompany());
            statement.setString(4, object.getAddress());
            statement.setString(5, object.getCity());
            statement.setString(6, object.getState());
            statement.setString(7, object.getCountry());
            statement.setString(8, object.getPostal_code());
            statement.setString(9, object.getPhone());
            statement.setString(10, object.getFax());
            statement.setString(11, object.getEmail());
            statement.setInt(12, object.getId());

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //number 7
    @Override
    public String findCountryWithMostCustomers() {
        /**
         * Return the country with the most customers.
         * @return The country with the most customers
         * @throws RuntimeException if there is an error with the database
         */

        String sql = "SELECT country, COUNT(*) AS count FROM customer GROUP BY country ORDER BY count DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

             PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return "Country: " +resultSet.getString("country") + " Count: "+ resultSet.getString("count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }




    //number 8
    @Override
    public String findCustomerWithHighestTotal() {
        /**
         * Return the customer with the highest total spent.
         * @return The customer with the highest total spent
         * @throws RuntimeException if there is an error with the database
         */

        String sql = "SELECT c.first_name, MAX(i.total) AS total_spent\n" +
                "FROM customer c\n" +
                "         JOIN invoice i ON c.customer_id = i.customer_id\n" +
                "GROUP BY c.customer_id\n" +
                "ORDER BY total_spent DESC\n" +
                "LIMIT 1";


        try (Connection connection = DriverManager.getConnection(url, username, password)) {

                PreparedStatement statement = connection.prepareStatement(sql);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    return "Customer: " +resultSet.getString("first_name") + " Total Spent: "+ resultSet.getString("total_spent");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
        }

        return null;
    }


}
