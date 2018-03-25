package mvc.repository;

import mvc.domain.User;
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
public class UserRepositor_y {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    // JavaオブジェクトとDBのマッピング
    private static final RowMapper<User> userRowMapper = (rs, i) -> {

        String userName = rs.getString("user_name");
        String encodedPassword = rs.getString("encoded_password");

        return null;//new User(userName, encodedPassword);
    };

    public User findOne(String userName) {

        SqlParameterSource param = new MapSqlParameterSource().addValue("user_name", userName);
        String query = "SELECT user_name, encoded_password FROM users WHERE user_name=:user_name";
        return jdbcTemplate.queryForObject(query, param, userRowMapper);
    }

    public User save(User user) {

        if (user == null) {
            return user;
        }

        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String insertQuery = "INSERT INTO users(user_name,encoded_password) values(:userName,:encodedPassword)";

        jdbcTemplate.update(insertQuery, param);

        return user;
    }

}
