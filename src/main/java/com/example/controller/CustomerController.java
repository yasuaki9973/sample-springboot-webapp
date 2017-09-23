/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import com.example.service.LoginUserDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    String create(@Validated Customer customer, BindingResult result, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {

        if (result.hasErrors()) {
            return list(model);
        }

        customerService.create(customer, userDetails.getUser().getUserName());
        return "redirect:/customers";
    }

    //
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    String editForm(@RequestParam Integer id, Model model) {

        Customer customer = customerService.findOne(id);
        model.addAttribute("customer", customer);

        return "customers/edit";
    }

    //
    @RequestMapping(value = "edit", params = "edit", method = RequestMethod.POST)
    String edit(@RequestParam Integer id, @Validated Customer customer, BindingResult result, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {

        if (result.hasErrors()) {
            return editForm(id, model);
        }

        customerService.update(customer, userDetails.getUser().getUserName());
        return "redirect:/customers";
    }

    //
    @RequestMapping(value = "edit", params = "goToTop", method = RequestMethod.POST)
    String goToTop() {
        return "redirect:/customers";
    }

    //
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    String delete(@RequestParam Integer id, Model model) {

        customerService.delete(id);
        return "redirect:/customers";
    }

}
