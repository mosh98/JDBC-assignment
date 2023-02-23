package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public AppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Requirement 1
        customerRepository.findAll().forEach(System.out::println);
        // Requirement 2
        System.out.println(customerRepository.findById(7));
        // Requirement 3
        System.out.println(customerRepository.findByName("Enrique"));
        // Requirement 4
        customerRepository.findCustomersPage(5, 0).forEach(System.out::println);
        // Requirement 5
        Customer customer = new Customer(50, "Ida", "Ruslanova", "Experis", "Sweden", "Stockholm", "Stockholm",  "ida@gmail.com");
        System.out.println(customerRepository.addCustomer(customer));
        // Requirement 6
        Customer customer1 = new Customer(50, "ZAkhida", "Ruslanova", "Experis", "Sweden", "Stockholm", "Stockholm",  "ida@gmail.com");
        System.out.println(customerRepository.updateCustomer(customer1));
        // Requirement 7
        System.out.println("Country with the most customers is " + customerRepository.findCountryWithMostCustomers());
        // Requirement 8
        System.out.println(customerRepository.findHighestSpender());
        // Requirement 9
        System.out.println(customerRepository.mostPopGenre(10));
    }
}
