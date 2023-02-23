package com.example.demo.repositories;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerCountry;
import com.example.demo.model.CustomersFavoriteGenre;
import com.example.demo.model.HighestSpender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    /**
     * This class is responsible for communicating with the database.
     * It is a repository class.
     * It is annotated with @Repository.
     * It is annotated with @Autowired.
     *
     */

    private final String url;
    private final String username;
    private final String password;

    @Autowired
    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // number 1
    @Override
    public List<Customer> findAll() {
        /**
         * Return a list of all customers from the database.
         * @return A list of all customers
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
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // number 2
    @Override
    public Customer findById(Integer id) {
        /**
         * Return a customer from the database with the given id.
         * @param id The id of the customer
         * @return A customer with the given id
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
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    // number 3
    @Override
    public Customer findByName(String name) {
        /**
         * Return a customer from the database with the given name.
         * @param name The name of the customer
         *             The name can be either the first name or the last name
         *             The name can be partial
         *             The name is case insensitive
         * @return A customer with the given name
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
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    // number 4
    @Override
    public List<Customer> findCustomersPage(int limit, int offset) {
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
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //number 5
    @Override
    public int addCustomer(Customer object) {
        //Make an sql string to insert into the database for customer
        /**
         * Add a customer to the database.
         * @param object The customer to add
         *               The customer id will be ignored
         *               The customer id will be generated by the database
         *               The customer id will be set in the returned customer
         * @return The customer that was added to the database
         */
        String sql = "INSERT INTO customer (first_name, last_name, company, country, postal_code, phone, email) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        int newCustomer = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, object.getFirst_name());
            statement.setString(2, object.getLast_name());
            statement.setString(3, object.getCompany());
            statement.setString(4, object.getCountry());
            statement.setString(5, object.getPostal_code());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getEmail());

            newCustomer = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newCustomer;
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

        String sql = "UPDATE customer SET first_name = ?, last_name = ?, company = ?, country = ?,  postal_code = ?, phone = ?, email = ? WHERE customer_id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.getFirst_name());
            statement.setString(2, object.getLast_name());
            statement.setString(3, object.getCompany());
            statement.setString(4, object.getCountry());
            statement.setString(5, object.getPostal_code());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getEmail());
            statement.setInt(8, object.getCustomer_id());

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
         *
         */

        String sql = """
                SELECT country
                FROM customer
                GROUP BY country
                ORDER BY count(country) DESC
                LIMIT 1;""";
        CustomerCountry country = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                country = new CustomerCountry(result.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country.getCountryName();
    }

    //number 8
    @Override
    public HighestSpender findHighestSpender() {
        /**
         * Return the customer with the highest total spent.
         * @return The customer with the highest total spent
         * @throws RuntimeException if there is an error with the database
         *
         */

        String sql = """
                SELECT  name, id, total
                FROM ( 
                    SELECT first_name as name, 
                    customer.customer_id as id, total
                    FROM customer
                    JOIN invoice 
                    ON customer.customer_id = invoice.customer_id) AS spenders
                WHERE total = (SELECT MAX(total) FROM invoice)""";

        HighestSpender highestSpender = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                highestSpender = new HighestSpender(
                        result.getInt("id"),
                        result.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return highestSpender;
    }

    //number 9
    @Override
    public CustomersFavoriteGenre mostPopGenre(int id) {
        /**
         * Return the customer's favorite genre.
         * @param id The customer id
         *           If the customer does not exist, return null
         *           If the customer has no invoices, return null
         * @return The customer's favorite genre
         * @throws RuntimeException if there is an error with the database
         */


        String sql = """
                WITH customer_genre_counts AS (
                SELECT c.customer_id, c.first_name, g.name AS genre_name, COUNT(g.genre_id) AS genre_count,
                RANK() OVER (PARTITION BY c.customer_id ORDER BY COUNT(g.genre_id) DESC) AS genre_rank
                FROM customer c
                JOIN invoice i ON i.customer_id = c.customer_id
                JOIN invoice_line il ON il.invoice_id = i.invoice_id
                JOIN track t ON t.track_id = il.track_id
                JOIN genre g ON g.genre_id = t.genre_id
                WHERE c.customer_id = ?
                GROUP BY c.customer_id, g.genre_id)
                SELECT customer_id, first_name, genre_name, genre_count
                FROM customer_genre_counts
                WHERE genre_rank = 1;""";
        CustomersFavoriteGenre customerFavGenre = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerFavGenre = new CustomersFavoriteGenre(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("genre_name")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customerFavGenre;
    }
}
