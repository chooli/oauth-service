package com.jumkid.oauthservice.dao;

import com.jumkid.oauthservice.model.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AuthorityDao {

    static Logger logger = LoggerFactory.getLogger(AuthorityDao.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuthorityDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Add new authority to the user
     *
     * @param authority
     * @return
     */
    public int add(Authority authority) {
        String updateQuery = "INSERT INTO authorities(username, role) " +
                "VALUES(:username, :role)";

        Map<String, Object> params = new HashMap<>();
        params.put(Authority.Fields.USERNAME.value(), authority.getUsername());
        params.put(Authority.Fields.ROLE.value(), authority.getRole());

        int row = namedParameterJdbcTemplate.update(updateQuery, params);
        logger.info("inserted new user authority {}", row);
        return row;
    }

    /**
     * Update multiple fields of user authority by the given username
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

        String queryForUpdate = "UPDATE authorities SET " + columnsUpdateStr + " WHERE username = ?";

        int row = jdbcTemplate.update(queryForUpdate, new Object[] {username});
        logger.debug("updated authority {}", row);
        return row;
    }

    private void buildQuery(StringBuilder columnsUpdateStr, String field, Object value) {
        columnsUpdateStr.append(Authority.Fields.getColumnName(field));
        columnsUpdateStr.append(" = ");
        if(value instanceof String){
            columnsUpdateStr.append("'" + value + "'");
        }else{
            columnsUpdateStr.append(value);
        }

        columnsUpdateStr.append(",");
    }

    /**
     * Fetch a list of roles by given username
     *
     * @return list of user roles
     */
    public List<String> getUserRoles(String username) {
        String query = "SELECT role FROM authorities WHERE username = ?";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query, new Object[] {username});
        logger.debug("query results {}", results.size());
        return results.stream().map( map -> (String)map.values().iterator().next() ).collect(Collectors.toList());
    }

    /**
     * Remove existing user's role by given username
     *
     * @param username, role
     * @return number of record(s) to be removed
     */
    public int removeRole(String username, String role) {
        String queryForDelete = "DELETE FROM authorities WHERE username = ? and role = ?";

        int row = jdbcTemplate.update(queryForDelete, username, role);
        logger.info("remove authority {}", row);
        return row;
    }

    /**
     * Remove existing user's roles by given username
     *
     * @param username
     * @return
     */
    public int removeRolesForUser(String username) {
        String queryForDelete = "DELETE FROM authorities WHERE username = ?";

        int row = jdbcTemplate.update(queryForDelete, username);
        logger.info("remove authority {}", row);
        return row;
    }

}
