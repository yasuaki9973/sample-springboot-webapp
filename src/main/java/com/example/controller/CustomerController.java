/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yasuaki
 */
@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ModelAttribute
    Customer setUpCustomer() {
        return new Customer();
    }

    //
    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) {

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    //
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@Validated Customer customer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return list(model);
        }

        customerService.create(customer);
        return "redirect:/customers";
    }

}
