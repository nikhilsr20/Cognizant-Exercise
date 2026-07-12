package com.cognizant.customerservice;

import com.cognizant.customerservice.entity.Customer;
import com.cognizant.customerservice.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CustomerServiceApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext container =
        SpringApplication.run(CustomerServiceApplication.class, args);

        Customer customer1 = Customer.builder()
                .email("rishabhdubey1472004@gmail.com")
                .name("Rishabh Dubey")
                .city("Bhopal")
                .build();
        Customer customer2 = Customer.builder()
                .email("rishbootdev@gmail.com")
                .name("RishBootDev")
                .city("Pune")
                .build();
        CustomerService serv = container.getBean(CustomerService.class);
        serv.createCustomer(customer1);
        serv.createCustomer(customer2);

    }

}
