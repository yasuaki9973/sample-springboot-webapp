/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import mvc.domain.Customer;
import mvc.service.CustomerService;
import mvc.service.LoginUserDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yasuaki
 */
@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    //顧客情報全件取得
    @RequestMapping(method = RequestMethod.GET)
    List<Customer> selectCustomers() {
        return customerService.findAll();
    }

    //顧客情報1件取得
    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    Customer selectCustomer(@PathVariable(value = "customerId") Integer customerId) {
        return customerService.findOne(customerId);
    }

    //顧客情報1件登録
    @RequestMapping(method = RequestMethod.POST)
    Customer createCustomer(@RequestBody Customer customer, @AuthenticationPrincipal LoginUserDetails userDetails) {
        return customerService.create(customer, userDetails.getUser().getUserName());
    }

    //顧客情報1件更新
    @RequestMapping(value = "{customerId}", method = RequestMethod.PUT)
    Customer updateCustomer(@PathVariable(value = "customerId") Integer customerId, @RequestBody Customer customer, @AuthenticationPrincipal LoginUserDetails userDetails) {
        customer.setId(customerId);
        return customerService.create(customer, userDetails.getUser().getUserName());
    }

    //顧客情報1件削除
    @RequestMapping(value = "{customerId}", method = RequestMethod.DELETE)
    void deleteCustomer(@PathVariable(value = "customerId") Integer customerId) {
        customerService.delete(customerId);
    }

}
