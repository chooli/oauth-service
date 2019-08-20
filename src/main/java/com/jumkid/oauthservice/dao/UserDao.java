package com.jumkid.oauthservice.dao;

import com.jumkid.oauthservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    static Logger logger = LoggerFactory.getLogger(UserDao.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder,
                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Add new user entity into database
     *
     * @param user
     * @return
     */
    public int add(User user) {
        String updateQuery = "INSERT INTO users(username, password, email, active) " +
                "VALUES(:username, :password, :email, :active)";

        Map<String, Object> params = new HashMap<>();
        params.put(User.Fields.USERNAME.value(), user.getUsername());
        params.put(User.Fields.PASSWORD.value(), passwordEncoder.encode(user.getPassword()));
        params.put(User.Fields.EMAIL.value(), user.getEmail());
        params.put(User.Fields.ACTIVE.value(), user.isActive());

        int row = namedParameterJdbcTemplate.update(updateQuery, params);
        logger.info("inserted new user {}", row);
        return row;
    }

    /**
     * Update multiple fields of user record by the given username
     *
     * @param username
     * @param properties
     * @return
     */
    public int updateFields(String username, Map<String, Object> properties) {
        StringBuilder columnsUpdateStr = new StringBuilder();
        //remove the last comma
        properties.forEach((field, value) -> buildQuery(columnsUpdateStr, field, value));
        if(columnsUpdateStr.length() > 0) columnsUpdateStr.setLength(columnsUpdateStr.length() - 1);

        String queryForUpdate = "UPDATE users SET " + columnsUpdateStr + " WHERE username = ?";

        int row = jdbcTemplate.update(queryForUpdate, new Object[] {username});
        logger.debug("updated user {}", row);
        return row;
    }

    private void buildQuery(StringBuilder columnsUpdateStr, String field, Object value) {
        columnsUpdateStr.append(User.Fields.getColumnName(field));
        columnsUpdateStr.append(" = ");
        if(User.Fields.PASSWORD.value().equals(field)){
            columnsUpdateStr.append("'" + passwordEncoder.encode((String)value) + "'");
        }else if(value instanceof String){
            columnsUpdateStr.append("'" + value + "'");
        }else{
            columnsUpdateStr.append(value);
        }

        columnsUpdateStr.append(",");
    }

    /**
     * Fetch a list of usernames
     *
     * @return list of usernames
     */
    public List<String> getAllUsernames() {
        String query = "SELECT username FROM users";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        logger.debug("query results {}", results.size());
        return results.stream().map( map -> (String)map.values().iterator().next() ).collect(Collectors.toList());
    }

    /**
     * Fetch a user by given username
     *
     * @param username
     * @return
     */
    public User get(String username) {
        String query = "SELECT username, password, email, active FROM users " +
                "WHERE username = ?";

        User user = jdbcTemplate.queryForObject(query, new Object[] {username}, new UserRowMapper());
        logger.debug("found user for username {}", username);
        return user;
    }

    /**
     * Remove existing user by given username
     *
     * @param username
     * @return
     */
    public int remove(String username) {
        String queryForDelete = "DELETE FROM users WHERE username = ?";

        int row = jdbcTemplate.update(queryForDelete, username);
        logger.info("remove user {}", row);
        return row;
    }

}
