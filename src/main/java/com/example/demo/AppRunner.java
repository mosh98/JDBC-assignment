package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public AppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //customerRepository.updateCustomer(new Customer(1, "Luís", "Gonzalez", "Embraer - Empresa Brasileira de Aeronáutica S.A.", "Av. Brigadeiro Faria Lima, 2170", "São José dos Campos", "SP", "Brazil", "12227-000", "+55 (12) 3923-5555", "+55 (12) 3923-5566", "luisg@embraer.com.br"));

        //62,Ida,Ruslanova,Experis,Farsta,Stockholm,Stockholm,Sweden,111,123,123,ida@gmail.com
        customerRepository.updateCustomer(new Customer(62, "Ida", "Ruslanova", "Experis", "Farsta", "Stockholm", "Stockholm", "Sweden", "111", "123", "123", "ida@ruslanova"));


        //System.out.println(customerRepository.findCountryWithMostCustomers());
        System.out.println(customerRepository.findCustomerWithHighestTotal());
        //customerRepository.findCountryWithMostCustomers();

    }
}
