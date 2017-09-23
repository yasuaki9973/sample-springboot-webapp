/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yasuaki
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;

    private String encodedPassword;

    //private List<Customer> customers;
}
