package com.example;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... strings) throws Exception {

        customerService.create(new Customer(null, "Hidetoshi", "Dekisugi"));
        customerService.create(new Customer(null, "Nobita", "Nobi"));
        customerService.create(new Customer(null, "Takeshi", "Goda"));
        customerService.create(new Customer(null, "Shizuka", "Minamoto"));

        List<Customer> list = customerService.findAll();

        for (Customer customer : list) {
            System.out.println(customer.getFirstName());
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
