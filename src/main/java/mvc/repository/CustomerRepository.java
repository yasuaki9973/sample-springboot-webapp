package mvc.repository;

import java.util.List;
import mvc.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author yasuaki
 */
@Mapper
public interface CustomerRepository {

    public List<Customer> findAll();

}
