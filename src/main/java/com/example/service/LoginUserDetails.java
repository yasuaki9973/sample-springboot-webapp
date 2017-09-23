/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.User;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author yasuaki
 */
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUserName(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
}
