/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.repository;

import mvc.domain.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yasuaki
 */
@Repository
@Transactional
public class CustomerRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    // JavaオブジェクトとDBのマッピング
    private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {

        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String userName = rs.getString("user_name");

        return new Customer(id, firstName, lastName, userName);
    };

    public List<Customer> findAll() {

        String query = "SELECT id, first_name, last_name, user_name FROM customers ORDER BY id";
        return jdbcTemplate.query(query, customerRowMapper);
    }

    public Customer findOne(Integer customerId) {

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        String query = "SELECT id, first_name, last_name, user_name FROM customers WHERE id=:id";

        return jdbcTemplate.queryForObject(query, param, customerRowMapper);
    }

    public Customer save(Customer customer) {

        if (customer == null) {
            return customer;
        }

        SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
        String insertQuery = "INSERT INTO customers(first_name,last_name,user_name) values(:firstName,:lastName,:userName)";
        String updateQuery = "UPDATE customers SET first_name=:firstName,last_name=:lastName,user_name=:userName WHERE id =:id";

        if (customer.getId() == null) {
            jdbcTemplate.update(insertQuery, param);
        } else {
            jdbcTemplate.update(updateQuery, param);
        }

        return customer;
    }

    public void delete(Integer customerId) {

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        String query = "DELETE FROM customers WHERE id=:id";

        jdbcTemplate.update(query, param);

    }
}
