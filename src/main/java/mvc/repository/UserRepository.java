package mvc.repository;

import mvc.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author yasuaki
 */
@Mapper
public interface UserRepository {

    public User findOne(String userName);

    public User save(User user);

}
