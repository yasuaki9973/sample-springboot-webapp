package com.example;

import com.example.domain.Customer;
import com.example.domain.User;
import com.example.service.CustomerService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... strings) throws Exception {

        String encodedPassword = new Pbkdf2PasswordEncoder().encode("root");
        userService.create(new User("root", encodedPassword));

        customerService.create(new Customer(null, "Hidetoshi", "Dekisugi", null), "root");
        customerService.create(new Customer(null, "Nobita", "Nobi", null), "root");
        customerService.create(new Customer(null, "Takeshi", "Goda", null), "root");
        customerService.create(new Customer(null, "Shizuka", "Minamoto", null), "root");

    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
