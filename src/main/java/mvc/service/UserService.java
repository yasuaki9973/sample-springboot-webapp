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

    public User selectUser(String userName) {
        return userRepository.selectUser(userName);
    }

    public List<User> selectUsers() {
        return userRepository.selectUsers();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

}
