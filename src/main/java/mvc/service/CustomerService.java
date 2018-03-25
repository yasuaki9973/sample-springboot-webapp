/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.service;

import mvc.domain.Customer;
import mvc.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yasuaki
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findOne(Integer customerId) {
        return customerRepository.findOne(customerId);
    }

    public Customer create(Customer customer, String userName) {
        customer.setUserName(userName);
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer, String userName) {
        customer.setUserName(userName);
        return customerRepository.save(customer);
    }

    public void delete(Integer customerId) {
        customerRepository.delete(customerId);
    }

}
