/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.service;

import mvc.domain.User;
import mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yasuaki
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findOne(String userName) {
        return userRepository.findOne(userName);
    }

    // 本メソッド呼び出し前にパスワードの暗号化をしておくこと
    public User create(User user) {
        return userRepository.save(user);
    }

}
