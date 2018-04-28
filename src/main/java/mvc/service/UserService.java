package mvc.service;

import java.util.List;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}
