package mvc.repository;

import java.util.List;
import mvc.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author yasuaki
 */
@Mapper
public interface UserRepository {

    public User findOne(String userName);

    public List<User> findAll();

    public void updateUser(User user);

    public void deleteUser(User user);

}
