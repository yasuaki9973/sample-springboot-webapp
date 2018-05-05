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

    public User selectUser(String userName);

    public List<User> selectUsers();

    public void updateUser(User user);

    public void deleteUser(User user);

}
